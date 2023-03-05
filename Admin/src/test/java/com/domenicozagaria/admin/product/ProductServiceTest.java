package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.exception.AlreadyInUseEntityException;
import com.domenicozagaria.admin.util.exception.ExceededStockException;
import com.domenicozagaria.admin.util.mapper.GenericDTOMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductDTOMapper productDTOMapper;
    @Mock
    private GenericDTOMapper<ProductDTO> genericDTOMapper;
    private AutoCloseable autoCloseable;
    private ProductService productService;
    private final String ENTITY_TEST_NAME = "test-name";
    private final String ENTITY_DIFFERENT_TEST_NAME = "test-name-different";

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository, productDTOMapper, genericDTOMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveProduct() {
        when(productRepository.existsByName(ENTITY_TEST_NAME)).thenReturn(false);
        assertDoesNotThrow(() -> productService.saveProduct(ENTITY_TEST_NAME, 1));
    }

    @Test
    void cantSaveProductNameAlreadyExists() {
        when(productRepository.existsByName(ENTITY_TEST_NAME)).thenReturn(true);
        assertThrows(AlreadyInUseEntityException.class,
                () -> productService.saveProduct(ENTITY_TEST_NAME, 1));
    }

    @Test
    void cantSaveProductStockLessThanZero() {
        when(productRepository.existsByName(ENTITY_TEST_NAME)).thenReturn(false);
        assertThrows(ExceededStockException.class,
                () -> productService.saveProduct(ENTITY_TEST_NAME, -1));
    }

    @Test
    void updateProductRenamingEntity() {
        when(Utility.findEntityById(productRepository, 1)).thenReturn(Optional.of(createMockProduct()));
        assertDoesNotThrow(() -> productService.updateProduct(1, ENTITY_DIFFERENT_TEST_NAME, 11));
    }

    @Test
    void updateProductRenamingEntityNameAlreadyExisting() {
        when(Utility.findEntityById(productRepository, 1)).thenReturn(Optional.of(createMockProduct()));
        when(productRepository.existsByName(ENTITY_DIFFERENT_TEST_NAME)).thenReturn(true);
        assertThrows(AlreadyInUseEntityException.class,
                () -> productService.updateProduct(1, ENTITY_DIFFERENT_TEST_NAME, 11));
    }

    @Test
    void updateProductUpdatingStock() {
        when(Utility.findEntityById(productRepository, 1)).thenReturn(Optional.of(createMockProduct()));
        assertDoesNotThrow(() -> productService.updateProduct(1, ENTITY_TEST_NAME, 0));
    }

    @Test
    void deleteProduct() {
        when(Utility.findEntityById(productRepository, 1)).thenReturn(Optional.of(createMockProduct()));
        assertDoesNotThrow(() -> productService.deleteProduct(1));
    }

    private Product createMockProduct() {
        Product product = new Product();
        product.setName(ENTITY_TEST_NAME);
        product.setStock(10);
        product.setCreatedAt(LocalDateTime.now());
        product.setId(1);
        return product;
    }
}