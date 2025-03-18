package com.pks.spring.security_6.controller;


import com.pks.spring.security_6.UserRepository;
import com.pks.spring.security_6.entity.User;
import com.pks.spring.security_6.model.CreateUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody CreateUser createUser){
        User userToSave = User
                .builder()
                .email(createUser.getEmail())
                .password(createUser.getPassword())
                .build();

        return userRepository.save(userToSave);
    }
}
