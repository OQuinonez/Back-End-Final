package com.example.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

@RestController
public class SignUpController {
    @Value("${app.salt}")
    private String salt;
    String createSessionKey() {
        String alphabet= "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*();[]{}\\|,./<>?`~-=_+";
        String sessionKey = "";
        Random random = new Random();
        int randomLen = 12+random.nextInt(9);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            sessionKey+=c;
        }
        return sessionKey;
    }
    @CrossOrigin
    @PostMapping("/SignUp")
    public User signUp(@RequestBody SignUp newUser){
        System.out.println(newUser.UserName);
        System.out.println(newUser.Email);
        System.out.println(newUser.pass_word);
        System.out.println(newUser.UAddress);

        String hashedPassword = BCrypt.hashpw(newUser.pass_word, salt);
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String sessionKey = "";
        Random random = new Random();
        int randomLen = 12+random.nextInt(9);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            sessionKey+=c;
        }
        System.out.println("Session Key: " + sessionKey);
        return UserRepository.CreatingUser(newUser.UserName,  newUser.UAddress,  newUser.Email, hashedPassword, sessionKey);
    }
}
