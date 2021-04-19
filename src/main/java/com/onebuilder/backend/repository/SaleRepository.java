package com.onebuilder.backend.repository;

import com.onebuilder.backend.entity.Sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT s FROM Sale s WHERE s.clientUID.UID = :clientUID")
    Optional<List<Sale>> findByClientUID(@Param(value = "clientUID")Long clientUID);

    Optional<Sale> findFirstByClientUID_UIDOrderByDateTimeDesc(Long clientUID);

}
