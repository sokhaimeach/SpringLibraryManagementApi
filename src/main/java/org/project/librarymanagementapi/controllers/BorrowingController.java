package org.project.librarymanagementapi.controllers;

import org.project.librarymanagementapi.services.BorrowingService;

public class BorrowingController {
    private final BorrowingService borrowingService;
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }
}
