package com.community.community.controllers;

import com.community.community.controllers.payloads.AuthResponse;
import com.community.community.controllers.payloads.MessageResponse;
import com.community.community.controllers.payloads.SignInData;
import com.community.community.controllers.payloads.SignUpData;
import com.community.community.security.JwtUtils;
import com.community.community.usermanagement.entity.User;
import com.community.community.usermanagement.entity.UserRole;
import com.community.community.usermanagement.repository.UserRepository;
import com.community.community.usermanagement.repository.UserRoleRepository;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = ["http://localhost:4200"])
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody @Valid SignInData loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new AuthResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList())
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody @Valid SignUpData signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.username)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        User user = new User(signUpRequest.username, encoder.encode(signUpRequest.password));

        // Verify roles exist
        List<String> rolesNotFound = new ArrayList<>();
        signUpRequest.roles.forEach(role -> {
            if (userRoleRepository.findById(role).isEmpty()) {
                rolesNotFound.add(role);
            }
        });
        if (rolesNotFound.size() > 0) {
            return ResponseEntity.badRequest().body(new MessageResponse(
                    String.format("Error: the roles %s do not exist", rolesNotFound)
            ));
        }

        // set roles
        user.setRoles(signUpRequest.roles.stream().map(roleName -> new UserRole(roleName)).collect(Collectors.toList()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
