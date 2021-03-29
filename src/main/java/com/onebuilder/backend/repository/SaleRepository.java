package com.onebuilder.backend.repository;

import com.onebuilder.backend.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<List<Sale>> findByClientUID(Long clientUID);
    Optional<Sale> findFirstByClientUIDOrderByDateTimeDesc(Long clientUID);

}
