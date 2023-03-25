package com.project.authentication.controller;

import com.project.authentication.core.exceptions.NotFoundRegisterException;
import jakarta.servlet.http.HttpServletRequest;
import src.dto.auth.AuthResponseDTO;
import src.dto.auth.LoginRequestDTO;
import src.dto.auth.RegisterRequestDTO;
import com.project.authentication.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.dto.auth.ValidUsernameDTO;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("verify-username")
    public ResponseEntity<?> verifyUsername(@RequestBody ValidUsernameDTO body){
        return ResponseEntity.ok(authService.validUsername(body.getUsername()));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto, HttpServletRequest req) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        AuthResponseDTO response = authService.login(dto, req.getRemoteAddr());

        if(response.getSuccess()){
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

}
