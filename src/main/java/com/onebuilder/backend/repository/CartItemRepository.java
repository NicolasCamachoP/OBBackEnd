package com.onebuilder.backend.repository;

import com.onebuilder.backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Transactional
    void deleteAllByCart_Id(Long id);

    @Transactional
    void deleteByCart_IdAndAndProductEAN(Long id, String EAN);
}
