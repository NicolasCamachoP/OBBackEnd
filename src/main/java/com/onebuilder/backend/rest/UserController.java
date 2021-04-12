package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.repository.UserRepository;
import com.onebuilder.backend.service.IUserService;
import com.onebuilder.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping
    List<User> findAllUsers(){
        return userRepository.findAll();
    }


}
