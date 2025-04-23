package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.DTO.LoginDTO;
import com.bridgelabz.address_book.DTO.RegisterDTO;
import com.bridgelabz.address_book.DTO.ResponseDTO;
import com.bridgelabz.address_book.models.User;
import com.bridgelabz.address_book.repository.UserRepository;
import com.bridgelabz.address_book.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserInterface{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private JWTUtils jwtUtils;


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
        mailService.sendMail("naman0913.be21@chitkara.edu.in", registerDTO.getEmail(), "Registration", "Successfull Registration :" + newUser) ;
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
                log.info("Login successful: "+user);
                mailService.sendMail("naman0913.be21@chitkara.edu.in", loginDTO.getUsername(), "Login", "Successfull Login :" + user.getName());

                return new ResponseEntity<>(new ResponseDTO("Login successful", user), HttpStatus.OK);
            }

            log.error("Login failed: "+user);
        }
        else{
            return new ResponseEntity<>(new ResponseDTO("No User exist with this email", null), HttpStatus.BAD_REQUEST);
        }
        mailService.sendMail("naman0913.be21@chitkara.edu.in", loginDTO.getUsername(), "Login Failed", " If you are not trying to login, please change the password: " );
        return new ResponseEntity<>(new ResponseDTO("Invalid username or password", null), HttpStatus.UNAUTHORIZED);
    }
}
