package org.project.librarymanagementapi.dto.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthorRequest {
    @NotBlank(message = "Name cannot be blank/null")
    @Size(min = 1, max = 100, message = "Name should be between 1 to 100")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be valid")
    private String email;

    public AuthorRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
