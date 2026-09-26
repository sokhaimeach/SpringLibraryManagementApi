package org.project.librarymanagementapi.services;

import org.project.librarymanagementapi.dto.role.RoleRequest;
import org.project.librarymanagementapi.entities.Role;
import org.project.librarymanagementapi.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void create(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());

        roleRepository.save(role);
    }
}
