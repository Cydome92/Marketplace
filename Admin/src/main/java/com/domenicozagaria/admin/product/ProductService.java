package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.dto.ProductDTO;
import com.domenicozagaria.exception.MissingEntityException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public ProductService(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    public void saveProduct(String name, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        Utility.saveEntity(productRepository, product);
    }

    //TODO gestione lista di tag
    public void updateProduct(int productId, String name, int stock) {
        Product product = findProductById(productId);
        product.setName(name);
        product.setStock(stock);
        Utility.saveEntity(productRepository, product);
    }

    private void deleteProduct(int productId) {
        Product product = findProductById(productId);
        Utility.deleteEntity(productRepository, product);
    }

    public ProductDTO getProductById(int id) {
        return Utility.findEntityById(productRepository, id)
                .map(productDTOMapper)
                .orElseThrow(MissingEntityException::new);
    }

    public List<ProductDTO> findAllProducts() {
        return Utility.mapCollectionTo(
                productRepository.findAll(),
                productDTOMapper,
                Collectors.toList()
        );
    }

    public List<ProductDTO> findAllProductsByTags(Set<Integer> tagIds) {
        return Utility.mapCollectionTo(
                productRepository.findAllByTagListIdIn(tagIds),
                productDTOMapper,
                Collectors.toList()
        );
    }

    private Product findProductById(Integer productId) {
        return Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
    }
}
