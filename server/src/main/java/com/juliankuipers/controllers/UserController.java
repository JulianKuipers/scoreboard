package com.juliankuipers.controllers;

import com.juliankuipers.entities.User;
import com.juliankuipers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewUser (@RequestParam String username,
                       @RequestParam String hashedPassword) {
        User n = new User();
        n.setUsername(username);
        n.setHashedPassword(hashedPassword);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/{username}")
    public @ResponseBody User getUsersByName(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
