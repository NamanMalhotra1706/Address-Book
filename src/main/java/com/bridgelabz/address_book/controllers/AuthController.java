package com.bridgelabz.address_book.controllers;


import com.bridgelabz.address_book.DTO.LoginDTO;
import com.bridgelabz.address_book.DTO.RegisterDTO;
import com.bridgelabz.address_book.services.JwtUserDetailsService;
import com.bridgelabz.address_book.services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserInterface userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

}
