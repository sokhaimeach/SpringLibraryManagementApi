package org.project.librarymanagementapi.repositories;

import org.project.librarymanagementapi.entities.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {
    List<Borrowing> findByOrderByCreatedAtDesc();

    List<Borrowing> findByMemberIdOrderByCreatedAtDesc(Long memberId);
}
