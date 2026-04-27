package com.turkcell.library_application.repository;

import com.turkcell.library_application.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
