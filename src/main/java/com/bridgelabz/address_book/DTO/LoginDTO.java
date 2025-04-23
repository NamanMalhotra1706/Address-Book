package com.bridgelabz.address_book.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    @NotBlank(message = "email can't empty")
    @Email(message = "Invalid email")
    private String username;

    @NotBlank(message = "password can't empty")
    @Size(min = 6, message = "password must be atleast 6 char long")
    private String password;
}
