package org.project.librarymanagementapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    @NotEmpty(message = "Email cannot be empty or null")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
