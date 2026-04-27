package com.turkcell.library_application.repository;

import com.turkcell.library_application.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
