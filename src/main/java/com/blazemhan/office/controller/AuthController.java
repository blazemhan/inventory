package com.blazemhan.office.controller;

import com.blazemhan.office.entity.User;
import com.blazemhan.office.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User request){

        return ResponseEntity.ok( authService.register(request));

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User request){

        return ResponseEntity.ok(authService.authenticate(request));
    }
}

