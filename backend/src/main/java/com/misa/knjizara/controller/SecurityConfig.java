package com.misa.knjizara.controller;

import com.misa.knjizara.entity.SecuredUser;
import com.misa.knjizara.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetails() {
        List<UserDetails> users = List.of(getUser());
        return new CustomInMemoryUserDetailsService(users);
    }

    private UserDetails getUser() {
        return new SecuredUser(new User("misa", "123"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
