package org.dailyreports.dto.user.login;

import org.dailyreports.dto.user.UserDto;

public record LoginResponseDto(UserDto userData, String token) {}