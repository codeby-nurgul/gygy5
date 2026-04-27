package com.turkcell.library_application.repository;

import com.turkcell.library_application.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
