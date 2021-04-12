package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.User;

public interface IUserService {
    void deleteUser(Long id);

    User updateUser(User user, Long id);

    User getUserById(Long id);

    User createUser(User user);

}