package com.pks.spring.security_6.controller;


import com.pks.spring.security_6.UserRepository;
import com.pks.spring.security_6.entity.User;
import com.pks.spring.security_6.model.CreateUserModel;
import com.pks.spring.security_6.model.LoginModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {
    
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody CreateUserModel createUser){
        User userToSave = User
                .builder()
                .email(createUser.getEmail())
                .password(createUser.getPassword())
                .build();

        return userRepository.save(userToSave);
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginModel loginModel){
        User userExists = userRepository.findByEmail(loginModel.getEmail());
      if(Objects.isNull(userExists))
          return "error";
      else return "success";
    }
}
