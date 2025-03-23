package org.dailyreports.mapper;

import org.dailyreports.dto.user.UserDto;
import org.dailyreports.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserDto toDto(User user);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(data.getPassword()))")
    @Mapping(target = "username", expression = "java(data.getUsername().toLowerCase())")
    @Mapping(target = "authorities", ignore = true)
    public abstract User toEntity(UserDto data, PasswordEncoder passwordEncoder);

}