package com.pks.spring.security_6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())// TO DISABLE csrf->csrf.disabled
                .authorizeHttpRequests(
                        request->request.anyRequest().authenticated()
                )//authenticate all request
                .formLogin(Customizer.withDefaults()) //use form login
                .httpBasic(Customizer.withDefaults());//use basic login
    return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails jide = User
                .withUsername("jide")
                        .password("{noop}password")
                                .roles("USER")
                                        .build();
        UserDetails nikky = User
                .withUsername("nikky")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(jide,nikky);
    }


}
