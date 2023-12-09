package com.bookstore.User.controller;

import com.bookstore.User.Repository.UserRepository;
import com.bookstore.User.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
@Component
@RestController
@RequestMapping("/user")
@CrossOrigin("http://ccfrontendbucket.s3-website.us-east-2.amazonaws.com/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signUp(@RequestBody User userDTO) {
        // Map userDTO to your User entity and save it to the database
        try{
            User user = new User(userDTO.getEmail(), userDTO.getPassword());
            userRepository.save(user);
        }
        catch(DataIntegrityViolationException e)
        {
            return "Duplicate user";
        }
        return "Success";
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        // Check user credentials and authenticate
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            existingUser.setError("Failure");
            return existingUser.getError();
        }
        return userRepository.findUserId(user.getEmail());
    }

}
