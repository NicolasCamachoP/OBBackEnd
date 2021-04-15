package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.exception.UserNotFoundException;
import com.onebuilder.backend.exception.WrongUserCredentialsException;
import com.onebuilder.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService implements IUserService {
    @Autowired
    private UserRepository repo;

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = repo.findById(id);
        if(user.isPresent()){
            repo.delete(user.get());
        }else{
            throw new UserNotFoundException(id);
        }

    }

    @Override
    public User updateUser(User user, Long id) {
        return repo.findById(id).map(provider -> {
            provider.setName(user.getName());
            provider.setEmail(user.getEmail());
            provider.setPassword(user.getPassword());
            provider.setSales(user.getSales());
            provider.setAdmin(user.getAdmin());
            provider.setToken(user.getToken());

            return repo.save(provider);
        }).orElseGet(() -> {
                    throw new UserNotFoundException(id);
                }
        );
    }

    @Override
    public User getUserById(Long id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> getUsers() { return repo.findAll(); }

    @Override
    public User loginUser(String email, String password) {
        System.out.println("Email: " + email + " Password: " + password);
        System.out.println("Users: " + repo.findAll());
        return repo.findByEmailAndPassword(email, password).orElseGet(() -> {
            throw new WrongUserCredentialsException();
        });
    }

}
