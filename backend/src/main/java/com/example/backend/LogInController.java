package com.example.backend;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Random;

@RestController
public class LogInController {
    @Value("${app.salt}")
    private String salt;
    @CrossOrigin
    @PostMapping("/login")
    public User logIn(@RequestBody LogIn currentUser) {
        System.out.println(currentUser.email);
        System.out.println(currentUser.password);
        String hashedPassword = BCrypt.hashpw(currentUser.password, salt);
        String sessionKey = new SignUpController().createSessionKey();
        User newMember = UserRepository.existingUser(currentUser.email, hashedPassword, sessionKey);
        System.out.println(currentUser.email);
        System.out.println(hashedPassword);
        System.out.println(sessionKey);
        if (newMember != null) {
            return newMember;
        } else {
            System.out.println("Something went Wrong!");
            return null;
        }
    }
}
