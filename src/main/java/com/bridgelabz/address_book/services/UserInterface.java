package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserInterface {
    ResponseEntity<ResponseDTO> register(RegisterDTO registerDTO);

    ResponseEntity<ResponseDTO> login(LoginDTO loginDTO);
    ResponseEntity<?> forgetPassword(String email);

    ResponseEntity<?> resetPassword(ResetPasswordDTO resetPasswordDTO);

    ResponseEntity<?> changePassword(ChangePassword changePassword, Authentication authentication);
}