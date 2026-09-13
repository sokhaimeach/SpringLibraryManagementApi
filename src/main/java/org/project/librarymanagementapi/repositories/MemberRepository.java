package org.project.librarymanagementapi.repositories;

import org.project.librarymanagementapi.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
