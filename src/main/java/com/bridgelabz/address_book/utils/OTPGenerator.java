package com.bridgelabz.address_book.utils;

import org.springframework.stereotype.Component;

@Component
public class OTPGenerator {
    public String generateOTP() {
        int otp = 100000 + (int) (Math.random() * 900000);
        return String.valueOf(otp);
    }
}
