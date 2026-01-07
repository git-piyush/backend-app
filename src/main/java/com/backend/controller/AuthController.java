package com.backend.controller;

import com.backend.dto.LoginRequest;
import com.backend.dto.RegistrationRequest;
import com.backend.dto.Response;
import com.backend.dto.TokenResponse;
import com.backend.entity.User;
import com.backend.exceptions.InvalidCredentialException;
import com.backend.exceptions.UserExistException;
import com.backend.repositories.UserRepository;
import com.backend.securities.CustomUserDetailsService;
import com.backend.securities.JwtUtils;
import com.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegistrationRequest request){
        if(request.getEmail()!=null){
            Optional<User> user = userRepository.findByEmail(request.getEmail());
            Response response = new Response();
            if(user.isPresent()){
                throw new UserExistException("User with email: "+request.getEmail()+" already exist.");
            }
        }
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest request){
      return ResponseEntity.status(HttpStatus.CREATED).body(userService.loginUser(request));
    }


    @GetMapping("/validate-token/{token}")
    public ResponseEntity<TokenResponse> validateToken(@PathVariable String token) {

        try {
            if (token != null) {
                String email = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
                if (StringUtils.hasText(email) && jwtUtils.isTokenValid(token, userDetails)) {
                    return ResponseEntity.ok(new TokenResponse("VALID"));
                }
            }
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenResponse("INVALID"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new TokenResponse("INVALID"));
    }


}
