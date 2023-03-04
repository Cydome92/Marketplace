package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.dto.GenericDTO;
import com.domenicozagaria.admin.util.exception.AlreadyInUseEntityException;
import com.domenicozagaria.admin.util.exception.ExceededStockException;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
import com.domenicozagaria.admin.util.mapper.GenericDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final GenericDTOMapper genericDTOMapper;

    public GenericDTO saveProduct(String name, int stock) {
        checkNameExists(name);
        checkStock(stock);
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        Utility.saveEntity(productRepository, product);
        return genericDTOMapper.apply(productDTOMapper.apply(product));
    }

    //TODO gestione lista di tag

    public void updateProduct(int productId, String name, int stock) {
        Product product = Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
        if (!product.getName().equalsIgnoreCase(name)) {
            checkNameExists(name);
        }
        checkStock(stock);
        product.setName(name);
        product.setStock(stock);
        Utility.saveEntity(productRepository, product);
    }
    public void deleteProduct(int productId) {
        Product product = Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
        Utility.deleteEntity(productRepository, product);
    }

    public ProductDTO getProductById(int id) {
        return Utility.findEntityById(productRepository, id)
                .map(productDTOMapper)
                .orElseThrow(MissingEntityException::new);
    }

    public Page<ProductDTO> getProducts(int pageNumber) {
        Pageable page = Utility.getPageable(pageNumber);
        return productRepository.findAll(page).map(productDTOMapper);
    }

    public Page<ProductDTO> getProductsByTagId(Integer tagId, int pageNumber) {
        Pageable page = Utility.getPageable(pageNumber);
        return productRepository.findAllByTagListId(tagId, page).map(productDTOMapper);
    }

    private void checkNameExists(String name) {
        boolean alreadyExists = productRepository.existsByName(name);
        if (alreadyExists) {
            throw new AlreadyInUseEntityException("Nome " + name + " già censito a sistema.");
        }
    }

    private void checkStock(Integer value) {
        if (value < 0) {
            throw new ExceededStockException();
        }
    }
}
