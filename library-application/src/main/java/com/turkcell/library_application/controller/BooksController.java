package com.turkcell.library_application.controller;

import com.turkcell.library_application.dto.CreateBookRequest;
import com.turkcell.library_application.dto.CreatedBookResponse;
import com.turkcell.library_application.dto.ListBookResponse;
import com.turkcell.library_application.dto.UpdateBookRequest;
import com.turkcell.library_application.service.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BookServiceImpl bookService;

    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public CreatedBookResponse create(@RequestBody CreateBookRequest request) {
        return bookService.create(request);
    }

    @GetMapping
    public List<ListBookResponse> getAll() {
        return bookService.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateBookRequest request) {
        bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }
}
