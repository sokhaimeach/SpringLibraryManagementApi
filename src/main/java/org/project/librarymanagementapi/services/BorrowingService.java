package org.project.librarymanagementapi.services;

import org.project.librarymanagementapi.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookCopyRepository bookCopyRepository;
    private final MemberRepository memberRepository;

    public BorrowingService(
            BorrowingRepository borrowingRepository,
            BookCopyRepository bookCopyRepository,
            MemberRepository memberRepository
    ) {
        this.borrowingRepository = borrowingRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.memberRepository = memberRepository;
    }


}
