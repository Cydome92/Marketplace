package com.domenicozagaria.admin.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    void findAllByCreatedAtBetween() {

    }

    @Test
    void findByName() {

    }

    @Test
    void findAllByProductListIdAndIsClosedFalse() {

    }
}