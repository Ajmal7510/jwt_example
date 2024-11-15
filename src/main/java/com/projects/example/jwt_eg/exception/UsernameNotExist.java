package com.projects.example.jwt_eg.exception;

public class UsernameNotExist extends RuntimeException{
    public UsernameNotExist(String message){
        super(message);
    }
}
