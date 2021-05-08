package com.onebuilder.backend.rest;

import com.onebuilder.backend.entityDTO.LoginObjectDTO;
import com.onebuilder.backend.entityDTO.UserDTO;
import com.onebuilder.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    UserDTO createUser(@RequestBody UserDTO newUser) {
        return userService.createUser(newUser);
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    List<UserDTO> findAllUsers() {
        return userService.getUsers();
    }

    @PostMapping("/login")
    @CrossOrigin
    UserDTO loginUser(@RequestBody LoginObjectDTO payload) {
        return userService.loginUser(
                payload.email,
                payload.password
        );
    }


}
