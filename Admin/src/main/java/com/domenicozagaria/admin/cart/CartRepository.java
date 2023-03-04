package com.domenicozagaria.admin.cart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Page<Cart> findAllByCreatedAtBetween(Pageable pageable, LocalDateTime startPeriod, LocalDateTime endPeriod);

    List<Cart> findAllByProductListIdAndIsClosedFalse(Integer productId);
}
