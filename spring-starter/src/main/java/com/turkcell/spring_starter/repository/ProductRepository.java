package com.turkcell.spring_starter.repository;

import com.turkcell.spring_starter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
