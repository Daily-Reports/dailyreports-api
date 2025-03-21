package org.dailyreports.service;

import lombok.Data;
import org.dailyreports.dto.user.UserDto;
import org.dailyreports.dto.user.login.LoginDto;
import org.dailyreports.dto.user.login.LoginResponseDto;
import org.dailyreports.exception.UsernameAlreadyUsedException;
import org.dailyreports.mapper.UserMapper;
import org.dailyreports.model.User;
import org.dailyreports.repository.UserRepository;
import org.dailyreports.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserDto register(UserDto data) {
        String username = data.getUsername().toLowerCase();

        userRepository.findByUsername(username).ifPresent(existingUser -> {
            throw new UsernameAlreadyUsedException();
        });

        User user = userMapper.toEntity(data, passwordEncoder);
        return userMapper.toDto(userRepository.save(user));
    }

    public LoginResponseDto login(LoginDto data) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDto(token);
    }

    public UserDto validate(String token) {
        String username = tokenService.validateToken(token);
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with this token, cannot be found"));

        return userMapper.toDto(user);
    }
}