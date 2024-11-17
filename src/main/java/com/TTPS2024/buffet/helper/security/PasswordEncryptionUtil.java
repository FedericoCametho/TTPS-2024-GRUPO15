package com.TTPS2024.buffet.helper.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptionUtil {


    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }


    public static boolean matchPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }

}
