package com.T2207A.T2207A_Spring.repositories;

import com.T2207A.T2207A_Spring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}