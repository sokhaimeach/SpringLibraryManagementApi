package org.project.librarymanagementapi.services;

import org.project.librarymanagementapi.dto.user.LoginRequest;
import org.project.librarymanagementapi.dto.user.LoginResponse;
import org.project.librarymanagementapi.dto.user.UserRequest;
import org.project.librarymanagementapi.dto.user.UserResponse;
import org.project.librarymanagementapi.entities.Role;
import org.project.librarymanagementapi.entities.User;
import org.project.librarymanagementapi.exceptions.ResourceNotFoundException;
import org.project.librarymanagementapi.repositories.RoleRepository;
import org.project.librarymanagementapi.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserResponse register(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());

        Role role = roleRepository.findByName(userRequest.getRole()).orElseThrow(
                () -> new ResourceNotFoundException("Role not found")
        );
        user.getRoles().add(role);

        // hash password before store in database
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userRepository.save(user);

        return toResponse(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail());
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
        System.out.println(user.getEmail());

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        // generate token
        String token = jwtService.generateToken(authentication);

        return new LoginResponse(token);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
