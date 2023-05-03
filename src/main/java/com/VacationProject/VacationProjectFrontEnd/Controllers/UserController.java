package com.VacationProject.VacationProjectFrontEnd.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.NoSuchElementException;

@Controller
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{user}")
    public ResponseEntity<User> checkByUserName(@PathVariable User givenUser){
        User user;
        try {
            user = userRepository.findByUsername(givenUser.getUsername()).orElseThrow(
                    () -> new NoSuchElementException(
                            "No such user with the given username: " + givenUser.getUsername()
                    )
            );
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(givenUser, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
