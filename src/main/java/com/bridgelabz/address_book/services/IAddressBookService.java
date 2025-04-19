package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.DTO.AddressBookDTO;
import com.bridgelabz.address_book.DTO.ResponseDTO;
import com.bridgelabz.address_book.models.AddressBook;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAddressBookService {
    AddressBook createAddressBook(AddressBookDTO addressBookDTO);
    List<AddressBook> getAllAddressBooks();
    String deleteAddressBook(int id);
    AddressBook updateAddressBook(int id, AddressBookDTO addressBookDTO);
    String deleteAllAddressBook();

    AddressBook getAddressBookById(int id);

    List<AddressBook> getAllAddressBookByCountry(String country);
 }
