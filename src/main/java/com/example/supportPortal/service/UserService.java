package com.example.supportPortal.service;

import com.example.supportPortal.exceptions.domain.EmailExistException;
import com.example.supportPortal.exceptions.domain.UserNameExistException;
import com.example.supportPortal.exceptions.domain.UserNotFoundException;
import com.example.supportPortal.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UserNameExistException, EmailExistException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail (String email);

}
