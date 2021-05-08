package com.onebuilder.backend.repository;

import com.onebuilder.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Optional<Product> findProductByEAN(String EAN);

    Optional<List<Product>> findByStockGreaterThan(Integer value);
}
