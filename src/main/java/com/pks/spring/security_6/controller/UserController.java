package com.pks.spring.security_6.controller;


import com.pks.spring.security_6.entity.User;
import com.pks.spring.security_6.model.CreateUserModel;
import com.pks.spring.security_6.model.LoginModel;
import com.pks.spring.security_6.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody CreateUserModel createUser){
          return userService.register(createUser);

    }


    @PostMapping("/login")
    public String login(@RequestBody LoginModel loginModel){
       return userService.login(loginModel);
    }
}
