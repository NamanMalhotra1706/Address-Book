package com.bridgelabz.address_book.DTO;

import com.bridgelabz.address_book.models.AddressBook;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressBookDTO {
    private int id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "State is mandatory")
    private String state;

    @NotBlank(message = "Zip is mandatory")
    private String zip;

    @Pattern(regexp = "^(?i)india$", message = "Country should be 'India' only")
    private String country;

    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits only")
    private String phoneNo;


    public AddressBookDTO(AddressBook addressBook) {
        this.id = addressBook.getId();
        this.name = addressBook.getName();
        this.address = addressBook.getAddress();
        this.email = addressBook.getEmail();
        this.city = addressBook.getCity();
        this.state = addressBook.getState();
        this.zip = addressBook.getZip();
        this.country = addressBook.getCountry();
        this.phoneNo = addressBook.getPhoneNo();
    }
}
