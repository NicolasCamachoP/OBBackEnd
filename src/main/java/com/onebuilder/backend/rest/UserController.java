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

    @PostMapping("/create/{role}")
    UserDTO createUser(@RequestBody UserDTO newUser, @PathVariable String role) {
        return userService.createUser(newUser, role);
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    List<UserDTO> findAllUsers() {
        return userService.getUsers();
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{email}")
    UserDTO findUser(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

}
