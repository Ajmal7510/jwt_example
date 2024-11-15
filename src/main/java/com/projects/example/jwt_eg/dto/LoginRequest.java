package com.projects.example.jwt_eg.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;

}
