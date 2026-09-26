package org.project.librarymanagementapi.dto.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be valid")
    private String email;

    @Size(min = 4, message = "Password at least contain 4 letter")
    private String password;
    private String role = "USER";
}
