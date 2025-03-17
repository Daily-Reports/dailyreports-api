package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.user.UserDto;
import com.vasconcellos.dailyreport.dto.user.login.LoginDto;
import com.vasconcellos.dailyreport.dto.user.login.LoginResponseDto;
import com.vasconcellos.dailyreport.exception.UsernameAlreadyUsedException;
import com.vasconcellos.dailyreport.mapper.UserMapper;
import com.vasconcellos.dailyreport.model.User;
import com.vasconcellos.dailyreport.repository.UserRepository;
import com.vasconcellos.dailyreport.security.TokenService;
import lombok.Data;
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
}