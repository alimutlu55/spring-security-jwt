package com.example.supportPortal.resource;

import com.example.supportPortal.exceptions.domain.EmailExistException;
import com.example.supportPortal.exceptions.domain.ExceptionHandling;
import com.example.supportPortal.exceptions.domain.UserNameExistException;
import com.example.supportPortal.exceptions.domain.UserNotFoundException;
import com.example.supportPortal.model.User;
import com.example.supportPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {
    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UserNameExistException, EmailExistException, MessagingException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }

}
