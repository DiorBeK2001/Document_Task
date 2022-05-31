package com.example.taskdoc.controller;

import com.example.taskdoc.model.domain.User;
import com.example.taskdoc.model.dto.UserDto;
import com.example.taskdoc.model.vm.ReqSignIn;
import com.example.taskdoc.security.JwtTokenProvider;
import com.example.taskdoc.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * User login
     */
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqSignIn request) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(((User) authentication.getPrincipal()));
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid UserDto dto) {
        log.debug("success added");
        return authService.registerUser(dto);
    }

}
