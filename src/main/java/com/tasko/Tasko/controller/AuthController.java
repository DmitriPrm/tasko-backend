package com.tasko.Tasko.controller;

import com.tasko.Tasko.dto.AuthRequest;
import com.tasko.Tasko.dto.AuthResponse;
import com.tasko.Tasko.dto.RegisterRequest;
import com.tasko.Tasko.model.Role;
import com.tasko.Tasko.model.User;
import com.tasko.Tasko.repository.RoleRepository;
import com.tasko.Tasko.repository.UserRepository;
import com.tasko.Tasko.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

//        Role userRole = roleRepository.findByName("ROLE_USER")
//                .orElseThrow(() -> new RuntimeException(("Error: Role USER not found")));
//        user.setRoles(Set.of(userRole));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtils.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
