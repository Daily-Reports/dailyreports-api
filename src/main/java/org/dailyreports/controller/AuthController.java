package org.dailyreports.controller;

import jakarta.validation.Valid;
import lombok.Data;
import org.dailyreports.dto.user.UserDto;
import org.dailyreports.dto.user.login.LoginDto;
import org.dailyreports.dto.user.login.LoginResponseDto;
import org.dailyreports.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("/validate")
    public ResponseEntity<UserDto> validate(@RequestParam String token) {
        return ResponseEntity.ok(userService.validate(token));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto data) {
        return ResponseEntity.ok(userService.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto data) {
        return ResponseEntity.ok(userService.register(data));
    }
}