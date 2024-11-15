package com.projects.example.jwt_eg.service;

import com.projects.example.jwt_eg.exception.UsernameNotExist;
import com.projects.example.jwt_eg.modal.UserInfo;
import com.projects.example.jwt_eg.modal.UserPrincipal;
import com.projects.example.jwt_eg.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotExist {

        UserInfo user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotExist("user not found");
        }
        return new UserPrincipal(user);
    }
}
