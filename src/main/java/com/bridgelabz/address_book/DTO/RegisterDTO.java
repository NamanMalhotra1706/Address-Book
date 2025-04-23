package com.bridgelabz.address_book.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDTO {

    @NotBlank()
    private String name;

    @NotBlank(message = "email can't empty")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "password can't empty")
    @Size(min = 6, message = "password must be atleast 6 char long")
    private String password;
}
