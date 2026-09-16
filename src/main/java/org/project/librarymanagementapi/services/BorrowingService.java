package org.project.librarymanagementapi.services;

import org.project.librarymanagementapi.dto.borrowing.BorrowingRequest;
import org.project.librarymanagementapi.dto.borrowing.BorrowingResponse;
import org.project.librarymanagementapi.entities.BookCopy;
import org.project.librarymanagementapi.entities.Borrowing;
import org.project.librarymanagementapi.entities.Member;
import org.project.librarymanagementapi.exceptions.ConflictException;
import org.project.librarymanagementapi.exceptions.ResourceNotFoundException;
import org.project.librarymanagementapi.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional
    public BorrowingResponse createBorrowing(BorrowingRequest borrowingRequest) {

        // check if member exist
        Member member = memberRepository.findById(borrowingRequest.getMemberId()).orElseThrow(
                () -> new ResourceNotFoundException("Member not found")
        );

        // check existing copy
        BookCopy copy = bookCopyRepository.findById(borrowingRequest.getBookCopyId()).orElseThrow(
                () -> new ResourceNotFoundException("Book copy not found")
        );

        // in case book not available
        if (!copy.getStatus()) {
            throw new ConflictException("Book copy is not available");
        }

        copy.setStatus(false);

        // create borrow object and saved to database
        Borrowing borrowing = new Borrowing();
        borrowing.setBookCopy(copy);
        borrowing.setMember(member);

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);

        return toResponse(savedBorrowing);
    }

    @Transactional(readOnly = true)
    public List<BorrowingResponse> getAllBorrowings() {
        List<Borrowing> borrowings = borrowingRepository.findAll();

        return borrowings.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public BorrowingResponse getBorrowingById(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Borrowing not found")
        );

        return toResponse(borrowing);
    }

    @Transactional
    public BorrowingResponse returnBorrowing(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Borrowing not found")
        );

        BookCopy copy = borrowing.getBookCopy();

        if (!copy.getStatus()) {
            throw new ConflictException("Borrow has been returned");
        }

        copy.setStatus(true);

        borrowing.setReturnedAt(LocalDateTime.now());

        return toResponse(borrowing);
    }

    @Transactional
    public void deleteBorrowing(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Borrowing not found")
        );

        if (!borrowing.getBookCopy().getStatus()) {
            throw new ConflictException("Can't delete borrowing because book copy is not available");
        }

        borrowingRepository.delete(borrowing);
    }

    private BorrowingResponse toResponse(Borrowing borrowing) {
        return new BorrowingResponse(
                borrowing.getId(),
                borrowing.getBookCopy().getId(),
                borrowing.getBookCopy().getBook().getTitle(),
                borrowing.getMember().getId(),
                borrowing.getMember().getName(),
                borrowing.getBorrowedAt(),
                borrowing.getReturnedAt()
        );
    }
}
