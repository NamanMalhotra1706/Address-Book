package com.bridgelabz.address_book.controllers;

import com.bridgelabz.address_book.DTO.AddressBookDTO;
import com.bridgelabz.address_book.DTO.ResponseDTO;
import com.bridgelabz.address_book.models.AddressBook;
import com.bridgelabz.address_book.services.IAddressBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans .factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/address-book")
public class AddressBookController {


    @Autowired
    private IAddressBookService addressBookService;

    @GetMapping("/getAllAddress")
    public ResponseEntity<ResponseDTO> getAllAddress() {
        log.info("getAllAddress");

        // Got Main AddressBook Model form Database
        List<AddressBook> addressBooks = addressBookService.getAllAddressBooks();

        // Converting in into DTO to send response
        List<AddressBookDTO> addressBookDTOS = new ArrayList<>();
        for (AddressBook addressBook : addressBooks) {
            AddressBookDTO addressBookDTO = new AddressBookDTO(addressBook);
            addressBookDTOS.add(addressBookDTO);
        }

        ResponseDTO responseDTO = new ResponseDTO("All Address", addressBookDTOS);
        log.info("getAllAddress" + responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAddressBook(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("createAddressBook");
        AddressBook addressBook = addressBookService.createAddressBook(addressBookDTO);
        log.error("createAddressBook" + addressBookDTO);
        AddressBookDTO addressBookDTO1 = new AddressBookDTO(addressBook);
        ResponseDTO responseDTO = new ResponseDTO("Address Book created successfully", addressBookDTO1);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("getAddress/{id}")
    public ResponseEntity<ResponseDTO> getAddress(@PathVariable int id) {
        log.info("getAddress");
        AddressBook addressBook = addressBookService.getAddressBookById(id);
        AddressBookDTO addressBookDTO = new AddressBookDTO(addressBook);

        ResponseDTO responseDTO = new ResponseDTO("AAddress Book found", addressBookDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("getAddress/country/{counrty}")
    public ResponseEntity<ResponseDTO> getAddressByCountry(@PathVariable String counrty) {
        log.info("getAddressByCountry");
        List<AddressBook> addressBookByCountry = addressBookService.getAllAddressBookByCountry(counrty);
        List<AddressBookDTO> addressBookDTOS = new ArrayList<>();
        for (AddressBook addressBook : addressBookByCountry) {
            AddressBookDTO addressBookDTO = new AddressBookDTO(addressBook);
            addressBookDTOS.add(addressBookDTO);
        }

        ResponseDTO responseDTO = new ResponseDTO("AAddress Book found", addressBookDTOS);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddressBook(@PathVariable int id) {
        log.info("Deleting AddressBook with id: " + id);
        String message = addressBookService.deleteAddressBook(id);
        ResponseDTO responseDTO = new ResponseDTO("AddressBook deleted of id : " + id , message);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllAddresses")
    public ResponseEntity<ResponseDTO> deleteAllAddresses() {
        String message = addressBookService.deleteAllAddressBook();
        ResponseDTO responseDTO = new ResponseDTO("All Addresses deleted", message);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateAddressBook(@PathVariable int id, @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Updating AddressBook with id: " + id);
        AddressBook addressBook = addressBookService.updateAddressBook(id, addressBookDTO);
        AddressBookDTO addressBookDTO1 = new AddressBookDTO(addressBook);
        ResponseDTO responseDTO = new ResponseDTO("Address Book updated successfully", addressBookDTO1);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
