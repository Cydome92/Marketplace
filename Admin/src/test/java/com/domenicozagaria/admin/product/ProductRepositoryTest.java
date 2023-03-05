package com.domenicozagaria.admin.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void existsByName() {
        final String TEST_NAME = "test-name";
        Product product = new Product();
        product.setName(TEST_NAME);
        product.setStock(10);
        productRepository.save(product);

        boolean exists = productRepository.existsByName(TEST_NAME);
        assertTrue(exists);
    }

    @Test
    void doesNotExistsByName() {
        final String TEST_NAME = "test-name";
        final String ANOTHER_TEST_NAME = "test-name-1";
        Product product = new Product();
        product.setName(TEST_NAME);
        product.setStock(10);
        productRepository.save(product);

        boolean exists = productRepository.existsByName(ANOTHER_TEST_NAME);
        assertFalse(exists);
    }
}