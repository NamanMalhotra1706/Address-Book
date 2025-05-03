package com.bridgelabz.address_book.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ChangePassword {

    String oldPassword;
    String newPassword;
    String confirmPassword;
}
