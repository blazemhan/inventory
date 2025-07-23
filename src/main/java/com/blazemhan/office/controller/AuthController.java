package com.blazemhan.office.controller;

import com.blazemhan.office.dtos.LoginRequest;
import com.blazemhan.office.dtos.RegisterRequest;
import com.blazemhan.office.entity.User;
import com.blazemhan.office.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.authenticate(request));
    }
}

