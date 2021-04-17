package com.onebuilder.backend.repository;

import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.clientUID.UID = :clientUID")
    Optional<List<Sale>> findByClientUID(Long clientUID);

    Optional<Sale> findFirstByClientUID_UIDOrderByDateTimeDesc(Long clientUID);

}
