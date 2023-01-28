package com.domenicozagaria.admin.product;

import com.domenicozagaria.dto.ProductDTO;
import com.domenicozagaria.exception.MissingEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

    @Test
    void findAllProducts() {
        saveBaseProductList();
        List<ProductDTO> productDTOS = productService.findAllProducts();
        Assertions.assertTrue(productDTOS.size() == 10);
    }

    private void saveBaseProductList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName(i + "-prodotto");
            product.setStock(i);
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }
}