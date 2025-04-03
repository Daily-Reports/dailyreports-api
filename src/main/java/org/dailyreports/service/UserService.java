package org.dailyreports.service;

import lombok.Data;
import org.dailyreports.dto.user.UserDto;
import org.dailyreports.dto.user.login.LoginDto;
import org.dailyreports.dto.user.login.LoginResponseDto;
import org.dailyreports.exception.EmailAlreadyUsedException;
import org.dailyreports.exception.UsernameAlreadyUsedException;
import org.dailyreports.mapper.UserMapper;
import org.dailyreports.model.Role;
import org.dailyreports.model.User;
import org.dailyreports.repository.UserRepository;
import org.dailyreports.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        String email = data.getEmail().toLowerCase();

        userRepository.findByUsername(username).ifPresent(existingUser -> {
            throw new UsernameAlreadyUsedException();
        });

        userRepository.findByEmail(email).ifPresent(existingUser -> {
            throw new EmailAlreadyUsedException();
        });

        User user = userMapper.toEntity(data, passwordEncoder);
        user.setRole(Role.USER);

        return userMapper.toDto(userRepository.save(user));
    }

    public LoginResponseDto login(LoginDto data) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);

        return new LoginResponseDto(userMapper.toDto(user), token);
    }

    public UserDto validate(String token) {
        String username = tokenService.validateToken(token);

        if (username == null || userRepository.findByUsername(username).isEmpty())
            return null;

        User user = userRepository.findByUsername(username).get();
        return userMapper.toDto(user);
    }
}