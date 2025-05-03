package com.bridgelabz.address_book.controllers;


import com.bridgelabz.address_book.DTO.ChangePassword;
import com.bridgelabz.address_book.DTO.LoginDTO;
import com.bridgelabz.address_book.DTO.RegisterDTO;
import com.bridgelabz.address_book.DTO.ResetPasswordDTO;
import com.bridgelabz.address_book.services.JwtUserDetailsService;
import com.bridgelabz.address_book.services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserInterface userService;

    @GetMapping("/helloworld")
    public String helloworld() {
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }


    @PostMapping("/forget-password")
    public ResponseEntity<?> forgotPassowrd(@RequestBody String email){
        return userService.forgetPassword(email);
    }

    @PutMapping("reset-password")
    public ResponseEntity<?> resetPassowrd(@RequestBody ResetPasswordDTO resetPasswordDTO){
        return userService.resetPassword(resetPasswordDTO);
    }

    @PutMapping("change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword, Authentication authentication){
        return userService.changePassword(changePassword, authentication);
    }

}
