package com.vasconcellos.dailyreport.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @NotNull
    private Role role = Role.USER;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authorities = new ArrayList<>();
        authorities.add("ROLE_USER");

        switch (role) {
            case ADMIN -> {
                authorities.add("ROLE_ADMIN");
                authorities.add("ROLE_SUPERVISOR");
            }
            case SUPERVISOR -> authorities.add("ROLE_SUPERVISOR");
            default -> throw new IllegalStateException("Unexpected value: " + role);
        }

        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }
}