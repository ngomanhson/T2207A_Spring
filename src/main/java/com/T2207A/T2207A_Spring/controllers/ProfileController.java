package com.T2207A.T2207A_Spring.controllers;

import com.T2207A.T2207A_Spring.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("/me")
    public ResponseEntity<User> profile(){
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
}
