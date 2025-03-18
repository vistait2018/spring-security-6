package com.pks.spring.security_6.service;

import com.pks.spring.security_6.UserRepository;
import com.pks.spring.security_6.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(Objects.isNull(user)){
            System.out.println("User not Available");
            throw new UsernameNotFoundException("User with email not found");
        }

        return new CustomUserDetails(user);
    }
}
