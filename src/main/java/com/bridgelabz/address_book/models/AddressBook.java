package com.bridgelabz.address_book.models;

import com.bridgelabz.address_book.DTO.AddressBookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String email;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNo;

    public AddressBook(AddressBookDTO dto) {
       this.name = dto.getName();
       this.address = dto.getAddress();
       this.email = dto.getEmail();
       this.city = dto.getCity();
       this.state = dto.getState();
       this.zip = dto.getZip();
       this.country = dto.getCountry();
       this.phoneNo = dto.getPhoneNo();
    }

}
