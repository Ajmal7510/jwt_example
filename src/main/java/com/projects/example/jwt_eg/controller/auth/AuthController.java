package com.projects.example.jwt_eg.controller.auth;

import com.projects.example.jwt_eg.dto.LoginRequest;
import com.projects.example.jwt_eg.dto.RegisterRequest;
import com.projects.example.jwt_eg.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/work")
    public String work(){
        return "work";
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws Exception {
        System.out.println(request);
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
