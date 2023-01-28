package com.domenicozagaria.admin.product;

import com.domenicozagaria.dto.ProductDTO;
import com.domenicozagaria.exception.MissingEntityException;
import com.domenicozagaria.admin.util.Utility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(String name, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        productRepository.save(product);
    }

    public ProductDTO getProductById(int id) throws Throwable {
        return (ProductDTO) Utility.findEntityById(productRepository, id)
                .map(Product::toDTO)
                .orElseThrow(MissingEntityException::new);
    }

    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(Product::toDTO)
                .toList();
    }
}
