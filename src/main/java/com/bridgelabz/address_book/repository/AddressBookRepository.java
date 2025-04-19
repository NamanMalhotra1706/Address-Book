package com.bridgelabz.address_book.repository;

import com.bridgelabz.address_book.models.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    @Query(value = "SELECT * from address_book WHERE country = :country " , nativeQuery = true)
    List<AddressBook> allAddressOfCountry(String country);
}
