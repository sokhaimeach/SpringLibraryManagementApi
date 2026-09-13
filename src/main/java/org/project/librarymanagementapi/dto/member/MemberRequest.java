package org.project.librarymanagementapi.dto.member;

import jakarta.validation.constraints.NotBlank;
import org.project.librarymanagementapi.dto.author.AuthorRequest;

public class MemberRequest extends AuthorRequest {

    @NotBlank(message = "Phone Number cannot be blank or null")
    private String phone;

    public MemberRequest(String name, String email, String phone) {
        super(name, email);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
