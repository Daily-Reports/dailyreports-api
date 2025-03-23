package org.dailyreports.dto.user.login;

import org.dailyreports.model.Role;

public record LoginResponseDto(String username, Role role, String token) {}