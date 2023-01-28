package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.exception.MissingEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    void saveProduct() {
        productService.saveProduct("test-new-product", 1);

        List<Product> allProducts = productRepository.findAll();
        Assertions.assertTrue(allProducts.size() > 0);
    }

    @Test
    void getProductById() {
        Assertions.assertThrows(MissingEntityException.class, () -> productService.getProductById(0));
    }
}