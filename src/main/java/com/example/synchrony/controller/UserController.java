package com.example.synchrony.controller;


import com.example.synchrony.model.User;
import com.example.synchrony.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestParam String username, @RequestParam String password) {
        return userService.registerUser(username, password);
    }

    @GetMapping("/profile")
    public User getUserProfile(@RequestParam String username) {
        return userService.findByUsername(username);
    }
}

