package com.bridgelabz.address_book.config;


import com.bridgelabz.address_book.utils.JWTRequestFilter;
import com.bridgelabz.address_book.utils.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    JWTRequestFilter jwtRequestFilter;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/auth/**", "/","/mail/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex-> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}