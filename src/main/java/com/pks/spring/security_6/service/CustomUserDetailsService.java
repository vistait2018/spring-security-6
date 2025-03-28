package com.pks.spring.security_6.service;

import com.pks.spring.security_6.UserRepository;
import com.pks.spring.security_6.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Objects;

@Component
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside Custom Service Details");
        log.info("User Name is " + username);
        User user = userRepository.findByEmail(username);
        log.info("User  is " + user);
        if(Objects.isNull(user)){
            System.out.println("User not Available");
            throw new UsernameNotFoundException("User with email not found");
        }

        return new CustomUserDetails(user);
    }
}
