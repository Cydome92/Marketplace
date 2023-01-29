package com.domenicozagaria.admin.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByTagList_IdIn(Set<Integer> tagIds);
}
