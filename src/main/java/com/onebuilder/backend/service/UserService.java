package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.UserDTO;
import com.onebuilder.backend.exception.EmailAlreadyTakenException;
import com.onebuilder.backend.exception.NotValidUserException;
import com.onebuilder.backend.exception.UserNotFoundException;
import com.onebuilder.backend.exception.WrongUserCredentialsException;
import com.onebuilder.backend.repository.RoleRepository;
import com.onebuilder.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private RoleRepository repoRole;
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    @Autowired
    private ICartService cartService;

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = repo.findById(id);
        if (user.isPresent()) {
            repo.delete(user.get());
        } else {
            throw new UserNotFoundException(id);
        }

    }

    @Override
    public UserDTO updateUser(UserDTO user, Long id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(repo.findById(id).map(provider -> {
            provider.setName(user.name);
            provider.setEmail(user.email);
            provider.setPassword(user.password);
            provider.setAdmin(user.isAdmin);
            return repo.save(provider);
        }).orElseGet(() -> {
                    throw new UserNotFoundException(id);
                }
        ), UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = repo.findByEmail(email);
        if (user.isPresent()) {
            return new ModelMapper().map(user.get(), UserDTO.class);
        } else {
            throw new UserNotFoundException(0l);
        }
    }

    @Override
    public UserDTO getUserDTOById(Long id) {
        Optional<User> foundUser = repo.findById(id);
        if (foundUser.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(foundUser.get(), UserDTO.class);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> foundUser = repo.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }
    @Override
    public User getUserFromCredentials(String email){
        Optional<User> foundUser = repo.findByEmail(email);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            throw new UserNotFoundException(0L);
        }
    }

    @Override
    public UserDTO createUser(UserDTO user, String role) {
        ModelMapper modelMapper = new ModelMapper();
        if (!repo.findByEmail(user.email).isPresent()) {
            try {
                User newUser = new User();
                newUser.setName(user.name);
                newUser.setAdmin(user.isAdmin);
                newUser.setEmail(user.email);
                newUser.setPassword(bCrypt.encode(user.password));
                newUser.setRole(repoRole.findByName(role).get());
                User userSaved = repo.save(newUser);
                cartService.createCart(userSaved);
                return modelMapper.map(userSaved, UserDTO.class);
            } catch (Exception e) {
                throw new NotValidUserException(e.getMessage());
            }
        } else {
            throw new EmailAlreadyTakenException(user.email);
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = repo.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return users.stream().map(
                user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO loginUser(String email, String password) {
        Optional<User> user = repo.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(user.get(), UserDTO.class);
        } else {
            throw new WrongUserCredentialsException();
        }
    }


}
