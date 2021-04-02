package com.onebuilder.backend.repository;

import com.onebuilder.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByStockGreaterThan(Integer value);
}
