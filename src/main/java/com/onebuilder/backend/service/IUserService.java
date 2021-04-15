package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    void deleteUser(Long id);

    User updateUser(User user, Long id);

    User getUserById(Long id);

    User createUser(User user);

    List<User> getUsers();

    User loginUser(String email, String password);

}
