package com.udacity.jwdnd.course1.cloudstorage.services;

import mapper.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;


@Service
public class AuthenticationService implements AuthenticationProvider{
    private final UserMapper userMapper;
    private final HashService hashService;

    @Autowired
    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Fetch user details
        User user = userMapper.getUser(username);
        if (user == null) {
            throw new BadCredentialsException("User not found.");
        }

        // Hash the provided password with the user's salt
        String encodedSalt = user.getSalt();
        String hashedPassword = hashService.getHashedValue(password, encodedSalt);

        // Check if the hashed password matches the stored password
        if (!user.getPassword().equals(hashedPassword)) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        // Create and return the authentication token if valid
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
