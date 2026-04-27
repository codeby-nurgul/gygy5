package com.turkcell.library_application.service;

import com.turkcell.library_application.dto.CreateBookRequest;
import com.turkcell.library_application.dto.CreatedBookResponse;
import com.turkcell.library_application.dto.ListBookResponse;
import com.turkcell.library_application.dto.UpdateBookRequest;
import com.turkcell.library_application.entity.Book;
import com.turkcell.library_application.entity.Category;
import com.turkcell.library_application.repository.BookRepository;
import com.turkcell.library_application.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public CreatedBookResponse create(CreateBookRequest request) {
        Book book = new Book();
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setYear(request.getYear());
        book.setCopies(request.getCopies());

        if (request.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
            book.setCategories(new HashSet<>(categories));
        }

        book = bookRepository.save(book);
        return new CreatedBookResponse(book.getId(), book.getTitle());
    }

    public List<ListBookResponse> getAll() {
        List<Book> books = bookRepository.findAll();
        List<ListBookResponse> responseList = new ArrayList<>();
        for (Book book : books) {
            Set<String> categoryNames = book.getCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.toSet());
            responseList.add(new ListBookResponse(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), categoryNames));
        }
        return responseList;
    }

    public void update(int id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setYear(request.getYear());
        book.setCopies(request.getCopies());

        if (request.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
            book.setCategories(new HashSet<>(categories));
        } else {
            book.setCategories(null);
        }

        bookRepository.save(book);
    }

    public void delete(int id) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
    }
}
