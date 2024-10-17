package com.dev.loginauthapi.controller;

import com.dev.loginauthapi.domain.user.User;
import com.dev.loginauthapi.dto.LoginRequestDTO;
import com.dev.loginauthapi.dto.ResponseDTO;
import com.dev.loginauthapi.infra.security.TokenService;
import com.dev.loginauthapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not Found!"));

        if(passwordEncoder.matches(user.getPassword(), body.password())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
