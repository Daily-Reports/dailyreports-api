package org.dailyreports.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.dailyreports.model.Role;

@Data
@Builder
public class UserDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 8, max = 128)
    private String password;

    @Email
    @NotBlank
    private String email;

    private Role role;

}