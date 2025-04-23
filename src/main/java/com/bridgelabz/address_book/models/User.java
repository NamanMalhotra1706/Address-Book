package com.bridgelabz.address_book.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    private String token;

//    public long getId(){
//        return id;
//    }
//    public void setId(long id){
//        this.id=id;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No roles for now
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}