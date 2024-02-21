package com.T2207A.T2207A_Spring.repositories;

import com.T2207A.T2207A_Spring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByNameContainingOrDescriptionContaining(String name, String search);

    List<Product> findAllByPrice(Integer price);

    @Query("SELECT p from Product p "+
            "WHERE (:name is NULL OR p.name LIKE %:name%) "+
            "AND (:minPrice is NULL OR p.price >= :minPrice) "+
            "AND (:maxPrice is NULL OR p.price <= :maxPrice) "
    )
    List<Product> filterProducts(@Param("name") String name,
                                 @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice);
}