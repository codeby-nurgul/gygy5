package com.turkcell.library_application.controller;

import com.turkcell.library_application.dto.CreateBorrowRequest;
import com.turkcell.library_application.dto.CreatedBorrowResponse;
import com.turkcell.library_application.dto.ListBorrowResponse;
import com.turkcell.library_application.service.BorrowServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowsController {
    private final BorrowServiceImpl borrowService;

    public BorrowsController(BorrowServiceImpl borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public CreatedBorrowResponse create(@RequestBody CreateBorrowRequest request) {
        return borrowService.create(request);
    }

    @GetMapping
    public List<ListBorrowResponse> getAll() {
        return borrowService.getAll();
    }
}
