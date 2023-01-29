package com.domenicozagaria.admin.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByCreatedAtBetween(LocalDateTime startPeriod, LocalDateTime endPeriod);

    Optional<Cart> findByName(String name);

    boolean existsByName(String name);
}