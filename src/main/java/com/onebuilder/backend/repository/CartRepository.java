package com.onebuilder.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onebuilder.backend.entity.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
