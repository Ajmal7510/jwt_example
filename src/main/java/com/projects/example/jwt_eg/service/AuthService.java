package com.projects.example.jwt_eg.service;

import com.projects.example.jwt_eg.dto.LoginRequest;
import com.projects.example.jwt_eg.dto.RegisterRequest;
import com.projects.example.jwt_eg.modal.Role;
import com.projects.example.jwt_eg.modal.UserInfo;
import com.projects.example.jwt_eg.repo.RoleRepository;
import com.projects.example.jwt_eg.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RoleRepository roleRepository;

//    public ResponseEntity<String> register(RegisterRequest request) throws Exception {
//
//       try {
//           UserInfo user=new UserInfo();
//
//           user.setUsername(request.getUserName());
//           user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//
//           Role role=roleRepository.findByName(request.getRole());
//           if(role==null){
//               Role newRole=new Role();
//               newRole.setName(request.getRole());
//               roleRepository.save(newRole);
//               role=roleRepository.findByName(request.getRole());
//           }
//
//           user.getRoles().add(role);
//           userRepository.save(user);
//
//           return ResponseEntity.ok("successfully registered user:"+user.getUsername());
//
//
//       }catch (Exception e){
//           throw new Exception();
//       }
//
//
//    }


    @Transactional
    public ResponseEntity<String> register(RegisterRequest request) {
        if (request == null || request.getUserName() == null || request.getPassword() == null || request.getRole() == null) {
            return ResponseEntity.badRequest().body("Invalid registration request. Missing required fields.");
        }

        try {
            // Create user instance
            UserInfo user = new UserInfo();
            user.setUsername(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            // Find or create role
            Role role = roleRepository.findByName(request.getRole());
            if (role == null) {
                role = new Role();
                role.setName(request.getRole());
                role = roleRepository.save(role); // Save and retrieve the saved role
            }

            // Assign role to user and save
            user.getRoles().add(role);
            userRepository.save(user);

            return ResponseEntity.ok("Successfully registered user: " + user.getUsername());
        } catch (Exception e) {
            // Log exception details for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration.");
        }
    }

    public ResponseEntity<String> login(LoginRequest request) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        String token = null;
        if (authentication.isAuthenticated())
            token=jwtService.generateToken(request.getUserName());
        return ResponseEntity.ok("success toke is:"+token);



    }
}
