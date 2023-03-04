package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.tag.TagService;
import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.tag.TagDTO;
import com.domenicozagaria.admin.util.exception.AlreadyInUseEntityException;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final TagService tagService;
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public ProductService(TagService tagService, ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.tagService = tagService;
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    public Integer saveProduct(String name, int stock) {
        boolean alreadyExists = productRepository.existsByName(name);
        if (alreadyExists) {
            throw new AlreadyInUseEntityException("Nome " + name + " già censito a sistema.");
        }
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        Utility.saveEntity(productRepository, product);
        return product.getId();
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
        Set<TagDTO> tags = tagService.findAllTagsBySetIds(tagIds);
        if (tags.size() != tagIds.size()) {
            throw new MissingEntityException();
        }
        Set<Integer> tagsId = Utility.mapCollectionTo(tags, TagDTO::getId, Collectors.toSet());
        return Utility.mapCollectionTo(
                productRepository.findAllByTagListIdIn(tagsId),
                productDTOMapper,
                Collectors.toList()
        );
    }

    private Product findProductById(Integer productId) {
        return Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
    }
}
