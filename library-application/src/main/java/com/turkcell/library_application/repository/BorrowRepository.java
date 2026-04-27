package com.turkcell.library_application.repository;

import com.turkcell.library_application.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
