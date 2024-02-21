package com.T2207A.T2207A_Spring.repositories;

import com.T2207A.T2207A_Spring.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
}
