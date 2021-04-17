package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.UserDTO;
import com.onebuilder.backend.exception.EmailAlreadyTakenException;
import com.onebuilder.backend.exception.NotValidUserException;
import com.onebuilder.backend.exception.UserNotFoundException;
import com.onebuilder.backend.exception.WrongUserCredentialsException;
import com.onebuilder.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UserDTO getUserById(Long id) {
        Optional<User> foundUser = repo.findById(id);
        if (foundUser.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(foundUser.get(), UserDTO.class);
        }else{
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        ModelMapper modelMapper = new ModelMapper();
        if (!repo.findByEmail(user.email).isPresent()){
            try {
                User newUser = new User();
                newUser.setName(user.name);
                newUser.setAdmin(user.isAdmin);
                newUser.setEmail(user.email);
                newUser.setPassword(user.password);
                return modelMapper.map(repo.save(newUser), UserDTO.class);
            }catch(Exception e){
                return new UserDTO();
                //throw new NotValidUserException(e.getMessage());
            }
        }else{
            return new UserDTO();
            //throw new EmailAlreadyTakenException(user.email);

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
        if (user.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            UserDTO userDTO =modelMapper.map(user.get(), UserDTO.class);
            return userDTO;
        } else{
            throw new WrongUserCredentialsException();
        }
    }

}
