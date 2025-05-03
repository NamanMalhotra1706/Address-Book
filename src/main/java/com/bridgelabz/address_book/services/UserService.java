package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.DTO.*;
import com.bridgelabz.address_book.models.User;
import com.bridgelabz.address_book.repository.UserRepository;
import com.bridgelabz.address_book.utils.JWTUtils;
import com.bridgelabz.address_book.utils.OTPGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserInterface {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private OTPGenerator otpGenerator;

    private String otp;


    @Override
    public ResponseEntity<ResponseDTO> register(RegisterDTO registerDTO) {
        String email = registerDTO.getEmail();
        String rawPassword = registerDTO.getPassword();

        if (userRepository.findByEmail(email).isPresent()) {
            return new ResponseEntity<>(
                    new ResponseDTO("Email already exists!", null),
                    HttpStatus.CONFLICT
            );
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Create user object
        User newUser = new User();
        newUser.setName(registerDTO.getName());
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);
        mailService.sendMail("naman0913.be21@chitkara.edu.in", registerDTO.getEmail(), "Registration", "Successfull Registration :" + newUser);
        return new ResponseEntity<>(new ResponseDTO("User registered successfully", 7), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> login(LoginDTO loginDTO) {

        String email = loginDTO.getUsername();
        String rawPassword = loginDTO.getPassword();

        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();

            if (passwordEncoder.matches(rawPassword, user.getPassword())) {

                log.info("Logged in as " + email);

                String token = jwtUtils.createJWTToken(email);

                log.info("Generated JWT token: " + token);

                user.setToken(token);
                userRepository.save(user);
                log.info("Login successful: " + user);
                mailService.sendMail("naman0913.be21@chitkara.edu.in", loginDTO.getUsername(), "Login", "Successfull Login :" + user.getName());

                return new ResponseEntity<>(new ResponseDTO("Login successful", user), HttpStatus.OK);
            }

            log.error("Login failed: " + user);
        } else {
            return new ResponseEntity<>(new ResponseDTO("No User exist with this email", null), HttpStatus.BAD_REQUEST);
        }
        mailService.sendMail("naman0913.be21@chitkara.edu.in", loginDTO.getUsername(), "Login Failed", " If you are not trying to login, please change the password: ");
        return new ResponseEntity<>(new ResponseDTO("Invalid username or password", null), HttpStatus.UNAUTHORIZED);
    }

    /*
     * @param
     * description
     * @return
     * */

    @Override
    public ResponseEntity<?> forgetPassword(String email) {

        log.info("Forget password email: " + email);

        // Check user exist hai ya nhi
        User user = userRepository.findByEmail(email).orElse(null);

        if(user==null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        //User Exist krta hai otp generate krke email pr bhejo
        otp = otpGenerator.generateOTP();
        String token = jwtUtils.createJWTToken(email);

        mailService.sendMail("naman0913.be21@chitkara.edu.in", email, "OTP generated", "OTP to reset password is: " + otp);

        return new ResponseEntity<>(new ResponseDTO("OTP generated  ", otp), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        String email = resetPasswordDTO.getEmail();

        User user = userRepository.findByEmail(email).orElse(null);

        //Verify OTP
        if(!otp.equals(resetPasswordDTO.getOtp())){
           return new ResponseEntity<>(new ResponseDTO("Invalid OTP",null), HttpStatus.BAD_REQUEST);
        }

        // New Pass ko encode krna hai
        if(!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())){
            return new ResponseEntity<>(new ResponseDTO("Confirm Password does not match",null), HttpStatus.BAD_REQUEST);
        }

        String encodedNewPassword = passwordEncoder.encode(resetPasswordDTO.getNewPassword());

        user.setPassword(encodedNewPassword);

        userRepository.save(user);

        otp="";

        mailService.sendMail("naman0913.be21@chitkara.edu.in" , email, "Password Request", "Password changed successfully.");
        return new ResponseEntity<>(new ResponseDTO("Reset password successful", user), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> changePassword(ChangePassword changePassword, Authentication authentication) {
        String email = authentication.getName();

        log.info("Change password email: " + email);

        User user = userRepository.findByEmail(email).orElse(null);

        if(!passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())){
           return new ResponseEntity<ResponseDTO>(new ResponseDTO("Old Password not matched",null), HttpStatus.BAD_REQUEST);
        }

        if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())){
            return new ResponseEntity<>(new ResponseDTO("Confirm Password not matched",null), HttpStatus.BAD_REQUEST);
        }

        String newEncodedPassword = passwordEncoder.encode(changePassword.getNewPassword());
        user.setPassword(newEncodedPassword);
        userRepository.save(user);

        mailService.sendMail("naman0913.be21@chitkara.edu.in" , email, "Change Password", "Password changed successfully");

        return new ResponseEntity<>(new ResponseDTO("Change password successful", user), HttpStatus.OK);

    }
}
