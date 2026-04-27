package com.turkcell.spring_starter.repository;

import com.turkcell.spring_starter.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}
