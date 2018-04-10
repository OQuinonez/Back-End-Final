package com.example.backend;

import org.springframework.web.bind.annotation.*;

@RestController
public class LogoutController {
    @CrossOrigin
    @PostMapping("/logout/{UserName}")
    public Boolean logout(@PathVariable String UserName) {
        return (UserRepository.updateSessionKey(UserName));
    }
}
