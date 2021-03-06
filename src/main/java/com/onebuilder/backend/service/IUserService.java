package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    void deleteUser(Long id);

    UserDTO updateUser(UserDTO user, Long id);

    UserDTO getUserByEmail(String email);

    UserDTO getUserDTOById(Long id);

    User getUserById(Long id);

    User getUserFromCredentials(String email);

    UserDTO createUser(UserDTO user, String role);

    List<UserDTO> getUsers();

    UserDTO loginUser(String email, String password);

}
