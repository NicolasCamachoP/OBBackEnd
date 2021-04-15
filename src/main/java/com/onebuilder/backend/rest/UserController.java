package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.repository.UserRepository;
import com.onebuilder.backend.service.IUserService;
import com.onebuilder.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping
    List<User> findAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/login")
    User loginUser(@RequestBody Map<String, Object> payload) {
        return userService.loginUser(
                (String)payload.get("email"),
                (String)payload.get("password")
        );
    }


}
