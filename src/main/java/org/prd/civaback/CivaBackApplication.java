package org.prd.civaback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CivaBackApplication implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CivaBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Password 1: " + passwordEncoder.encode("1234"));
        System.out.println("Password 2: " + passwordEncoder.encode("1234"));
    }
}