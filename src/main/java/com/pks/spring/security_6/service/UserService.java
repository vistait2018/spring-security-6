package com.pks.spring.security_6.service;


import com.pks.spring.security_6.UserRepository;
import com.pks.spring.security_6.entity.User;
import com.pks.spring.security_6.model.CreateUserModel;
import com.pks.spring.security_6.model.LoginModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public User register(CreateUserModel createUserModel){
        User userToSave = User
                .builder()
                .email(createUserModel.getEmail())
                .password(passwordEncoder.encode(createUserModel.getPassword()))
                .build();
        return userRepository.save(userToSave);
    }

    public String login(LoginModel loginModel){
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getEmail(), loginModel.getPassword()
                ));
        // User userExists = userRepository.findByEmail(loginModel.getEmail());
        if(!authenticate.isAuthenticated())
            return "error";
        else return jwtService.generateToken(loginModel);
    }



}
