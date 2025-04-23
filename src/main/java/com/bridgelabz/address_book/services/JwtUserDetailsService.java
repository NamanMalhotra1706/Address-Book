package com.bridgelabz.address_book.services;

import com.bridgelabz.address_book.models.User;
import com.bridgelabz.address_book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user=userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+email));

        return user;
    }
}
