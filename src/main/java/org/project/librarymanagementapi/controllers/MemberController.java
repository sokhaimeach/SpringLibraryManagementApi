package org.project.librarymanagementapi.controllers;

import jakarta.validation.Valid;
import org.project.librarymanagementapi.dto.ApiResponse;
import org.project.librarymanagementapi.dto.member.MemberRequest;
import org.project.librarymanagementapi.dto.member.MemberResponse;
import org.project.librarymanagementapi.services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberResponse>>> getMembers() {
        return ResponseEntity.ok(new ApiResponse<>(
                "Members fetched successfully",
                memberService.getAll()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponse>> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Member fetched successfully",
                memberService.getById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponse>> createMember(@Valid @RequestBody MemberRequest memberRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Member created successfully",
                        memberService.create(memberRequest),
                        HttpStatus.CREATED
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponse>> updateMember(
            @PathVariable Long id, @Valid @RequestBody MemberRequest memberRequest
    ) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Member updated successfully",
                memberService.update(id, memberRequest)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>("Member deleted successfully"));
    }
}
