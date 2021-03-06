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
        String hashedPassword = BCrypt.hashpw(currentUser.password, salt);
        String sessionKey = new SignUpController().createSessionKey();
        User newMember = UserRepository.existingUser(
                sessionKey,
                currentUser.email,
                hashedPassword);
        if (newMember != null) {
            System.out.println("No Worries, LoggIn works");
            return newMember;
        } else {
            System.out.println("Something went Wrong With LOGIN!");
            return null;
        }
    }
}
