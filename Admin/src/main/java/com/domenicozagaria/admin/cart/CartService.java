package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.product.Product;
import com.domenicozagaria.admin.product.ProductRepository;
import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.dto.CartDTO;
import com.domenicozagaria.exception.MissingEntityException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDTOMapper cartDTOMapper;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartDTOMapper cartDTOMapper,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartDTOMapper = cartDTOMapper;
        this.productRepository = productRepository;
    }

    public List<CartDTO> findCartByPeriod(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        return cartRepository.findAllByCreatedAtBetween(startPeriod, endPeriod)
                .stream()
                .map(cartDTOMapper)
                .toList();
    }

    public CartDTO findCartByName(String name) {
        return cartRepository.findByName(name)
                .map(cartDTOMapper)
                .orElseThrow(MissingEntityException::new);
    }

    public CartDTO findCartById(int id) {
        return Utility.findEntityById(cartRepository, id)
                .map(cartDTOMapper)
                .orElseThrow(MissingEntityException::new);
    }

    public void saveCart(String name) {
        Cart cart = new Cart();
        cart.setName(name);
        Utility.saveEntity(cartRepository, cart);
    }

    public void addProductToCart(int cartId, int productId) {
        Cart cart = Utility.findEntityById(cartRepository, cartId)
                .orElseThrow(MissingEntityException::new);
        Product product = Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
        cart.getProductList().add(product);
        Utility.saveEntity(cartRepository, cart);
    }
}
