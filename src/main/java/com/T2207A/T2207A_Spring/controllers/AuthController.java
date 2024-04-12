package com.T2207A.T2207A_Spring.controllers;

import com.T2207A.T2207A_Spring.entities.User;
import com.T2207A.T2207A_Spring.models.LoginResponse;
import com.T2207A.T2207A_Spring.models.LoginUser;
import com.T2207A.T2207A_Spring.models.RegisterUser;
import com.T2207A.T2207A_Spring.services.AuthenticationService;
import com.T2207A.T2207A_Spring.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUser request) {
        User registerUser = authenticationService.signup(request);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUser request){
        User user = authenticationService.authenticate(request);
        String jwtToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
