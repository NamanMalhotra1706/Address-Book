package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.DTO.AddressBookDTO;
import com.bridgelabz.address_book.models.AddressBook;
import com.bridgelabz.address_book.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookServiceImp implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Override
    public AddressBook createAddressBook(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook(addressBookDTO);
        return addressBookRepository.save(addressBook);
    }

    @Override
    public List<AddressBook> getAllAddressBooks(){
        return addressBookRepository.findAll();
    }

    @Override
    public AddressBook getAddressBookById(int id){
        AddressBook addressBook = addressBookRepository.findById((long) id).orElseThrow(()->new RuntimeException("AddressBook not found"));
        return addressBook;
    }

    @Override
    public List<AddressBook> getAllAddressBookByCountry(String country){
        List<AddressBook> addressBooksByCountry = addressBookRepository.allAddressOfCountry(country);
        return addressBooksByCountry;
    }

    @Override
    public String deleteAddressBook(int id) {
        addressBookRepository.deleteById((long) id);
        return "AddressBook deleted";
    }

    @Override
    public String deleteAllAddressBook(){
        addressBookRepository.deleteAll();
        return "All AddressBook deleted";
    }

    @Override
    public AddressBook updateAddressBook(int id, AddressBookDTO addressBookDTO) {
        AddressBook addressBook = addressBookRepository.findById((long) id).orElseThrow(()->new RuntimeException("AddressBook not found"));

        addressBook.setAddress(addressBookDTO.getAddress());
        addressBook.setName(addressBookDTO.getName());
        addressBook.setEmail(addressBookDTO.getEmail());
        addressBook.setPhoneNo(addressBookDTO.getPhoneNo());
        addressBook.setCity(addressBookDTO.getCity());
        addressBook.setState(addressBookDTO.getState());
        addressBook.setZip(addressBookDTO.getZip());
        addressBook.setCountry(addressBookDTO.getCountry());

       return addressBookRepository.save(addressBook);

    }
}
