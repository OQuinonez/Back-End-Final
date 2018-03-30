package com.example.backend;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class LogInController {
    @Value("${app.salt}")
    private String salt;
    @CrossOrigin(allowedHeaders="*",allowCredentials="true")
    @PostMapping("/LogIn/")
    public User logIn(@RequestParam SignUp newUser){
        String hashedPassword = BCrypt.hashpw(newUser.pass_word, salt);
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String sessionKey = "";
        Random random = new Random();
        int randomLen = 12+random.nextInt(9);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            sessionKey+=c;
        }
        User newMember = UserRepository.existingUser(newUser.Email,
                hashedPassword, sessionKey);
        if(newMember != null){
            return newMember;
        } else {
            System.out.println("There was an error");
            return null;
        }
    }
}
