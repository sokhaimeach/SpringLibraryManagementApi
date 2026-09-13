package org.project.librarymanagementapi.services;

import org.project.librarymanagementapi.dto.member.MemberRequest;
import org.project.librarymanagementapi.dto.member.MemberResponse;
import org.project.librarymanagementapi.entities.Member;
import org.project.librarymanagementapi.exceptions.ResourceNotFoundException;
import org.project.librarymanagementapi.repositories.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse create(MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setPhone(memberRequest.getPhone());

        memberRepository.save(member);

        return toResponse(member);
    };

    @Transactional(readOnly = true)
    public List<MemberResponse> getAll() {
        List<Member> members = memberRepository.findAll();

        return members.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MemberResponse getById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Memeber not found")
        );
        return toResponse(member);
    }

    @Transactional
    public MemberResponse update(Long id, MemberRequest memberRequest) {
        Member existingMember = memberRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Member not found")
        );

        existingMember.setName(memberRequest.getName());
        existingMember.setEmail(memberRequest.getEmail());
        existingMember.setPhone(memberRequest.getPhone());

        memberRepository.save(existingMember);
        return toResponse(existingMember);
    }

    @Transactional
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found");
        }

        memberRepository.deleteById(id);
    }

    private MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }


}
