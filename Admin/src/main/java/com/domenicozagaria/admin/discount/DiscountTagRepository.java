package com.domenicozagaria.admin.discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountTagRepository extends JpaRepository<DiscountTag, Integer> {
}
