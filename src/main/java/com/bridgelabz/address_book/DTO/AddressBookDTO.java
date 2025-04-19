package com.bridgelabz.address_book.DTO;

import com.bridgelabz.address_book.models.AddressBook;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressBookDTO {
    private int id;
    private String name;
    private String address;
    private String email;
    private String city;
    private String state;
    private String zip;

    @Pattern(regexp = "INDIA|india|India", message = "Country should be 'INDIA' only")
    private String country;

    @Pattern(regexp = "\\d{10}}", message = "Phone number should be 10 digits only")
    private int phoneNo;


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
