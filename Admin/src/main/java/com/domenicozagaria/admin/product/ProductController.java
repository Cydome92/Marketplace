package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericDTO saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.saveProduct(productDTO.getName(), productDTO.getStock());
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("{productId}")
    public ProductDTO getProduct(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @GetMapping
    public Page<ProductDTO> getProducts(@RequestParam @NotNull Integer page) {
        return productService.getProducts(page);
    }

    @PatchMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid ProductDTO productDto, @PathVariable int productId) {
        productService.updateProduct(productId, productDto.getName(), productDto.getStock());
    }
}
