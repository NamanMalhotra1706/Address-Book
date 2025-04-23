package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.DTO.LoginDTO;
import com.bridgelabz.address_book.DTO.RegisterDTO;
import com.bridgelabz.address_book.DTO.ResponseDTO;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface UserInterface {
    ResponseEntity<ResponseDTO> register(RegisterDTO registerDTO);
    ResponseEntity<ResponseDTO> login(LoginDTO loginDTO);
}
