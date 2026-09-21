package org.project.librarymanagementapi.services;


import org.project.librarymanagementapi.dto.author.AuthorRequest;
import org.project.librarymanagementapi.dto.author.AuthorResponse;
import org.project.librarymanagementapi.entities.Author;
import org.project.librarymanagementapi.exceptions.ResourceNotFoundException;
import org.project.librarymanagementapi.repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public AuthorResponse create(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setEmail(authorRequest.getEmail());

        return toResponse(authorRepository.save(author));
    }

    @Transactional(readOnly = true)
    public Page<AuthorResponse> getAll(int page, int size, String name) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Author> authors = authorRepository.searchByNameContainingIgnoreCase(
                name.trim(), pageable);

        return authors.map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public AuthorResponse getById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author not found with id: " + id)
        );

        return toResponse(author);
    }

    @Transactional
    public AuthorResponse update(Long id, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author not found with id: " + id)
        );

        author.setName(authorRequest.getName());
        author.setEmail(authorRequest.getEmail());

        authorRepository.save(author);

        return toResponse(author);
    }

    @Transactional
    public void delete(Long id) {
        if(!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }

        authorRepository.deleteById(id);
    }

    private AuthorResponse toResponse(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getCreatedAt(),
                author.getUpdatedAt()
        );
    }

}
