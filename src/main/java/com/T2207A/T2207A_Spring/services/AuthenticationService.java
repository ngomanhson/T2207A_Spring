package com.T2207A.T2207A_Spring.services;

import com.T2207A.T2207A_Spring.entities.User;
import com.T2207A.T2207A_Spring.models.LoginUser;
import com.T2207A.T2207A_Spring.models.RegisterUser;
import com.T2207A.T2207A_Spring.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signup(RegisterUser input) {
        User user = User.builder()
                .email(input.email)
                .password(passwordEncoder.encode(input.password))
                .full_name(input.fullName)
                .build();
        return userRepository.save(user);
    }

    public User authenticate(LoginUser input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        User user = userRepository.findByEmail(input.getEmail());
        if (user == null) throw new UsernameNotFoundException("Email or Password is not correct.");
        return user;
    }
}
