package org.project.librarymanagementapi.dto.role;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleRequest {
    @NotEmpty(message = "Role name cannot be empty or null")
    private String name;
}
