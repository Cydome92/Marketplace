package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.tag.TagDTO;
import com.domenicozagaria.admin.tag.TagService;
import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.dto.GenericDTO;
import com.domenicozagaria.admin.util.exception.AlreadyInUseEntityException;
import com.domenicozagaria.admin.util.exception.ExceededStockException;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
import com.domenicozagaria.admin.util.mapper.GenericDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final int PAGE_SIZE = 50;
    private final TagService tagService;
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final GenericDTOMapper genericDTOMapper;

    public GenericDTO saveProduct(String name, int stock) {
        checkName(name);
        checkStock(stock);
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        Utility.saveEntity(productRepository, product);
        return genericDTOMapper.apply(productDTOMapper.apply(product));
    }

    //TODO gestione lista di tag

    public void updateProduct(int productId, String name, int stock) {
        Product product = findProductById(productId);
        if (!product.getName().equalsIgnoreCase(name)) {
            checkName(name);
        }
        checkStock(stock);
        product.setName(name);
        product.setStock(stock);
        Utility.saveEntity(productRepository, product);
    }
    public void deleteProduct(int productId) {
        Product product = findProductById(productId);
        Utility.deleteEntity(productRepository, product);
    }

    public ProductDTO getProductById(int id) {
        return Utility.findEntityById(productRepository, id)
                .map(productDTOMapper)
                .orElseThrow(MissingEntityException::new);
    }

    public Page<ProductDTO> findAllProducts(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, PAGE_SIZE);
        return productRepository.findAll(page).map(productDTOMapper);
    }

    public Page<ProductDTO> findAllProductsByTags(Set<Integer> tagIds, int pageNumber) {
        Set<TagDTO> tags = tagService.findAllTagsBySetIds(tagIds);
        if (tags.size() != tagIds.size()) {
            throw new MissingEntityException();
        }
        Set<Integer> tagsId = Utility.mapCollectionTo(tags, TagDTO::getId, Collectors.toSet());
        Pageable page = PageRequest.of(pageNumber, PAGE_SIZE);
        return productRepository.findAllByTagListIdIn(tagsId, page).map(productDTOMapper);
    }

    private void checkName(String name) {
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

    private Product findProductById(Integer productId) {
        return Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
    }
}
