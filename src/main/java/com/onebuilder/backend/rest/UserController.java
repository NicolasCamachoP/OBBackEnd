package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.LoginObjectDTO;
import com.onebuilder.backend.entityDTO.UserDTO;
import com.onebuilder.backend.repository.UserRepository;
import com.onebuilder.backend.service.IUserService;
import com.onebuilder.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/login")
    @CrossOrigin
    UserDTO loginUser(@RequestBody LoginObjectDTO payload) {
        System.out.println(payload.toString());
        return userService.loginUser(
                payload.email,
                payload.password
        );
    }


}
