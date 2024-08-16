package com.fpt.lab3.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword {

    public static BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        String password = "123456";
        String encryptPass = getBCryptPasswordEncoder().encode(password);
        System.out.println("password:" + encryptPass);
    }
}
