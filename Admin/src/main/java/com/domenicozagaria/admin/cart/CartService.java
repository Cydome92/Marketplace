package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.product.Product;
import com.domenicozagaria.admin.product.ProductRepository;
import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.exception.ExceededStockException;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
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

    public CartDTO createCart(String name) {
        Cart cart = new Cart();
        cart.setName(name);
        Utility.saveEntity(cartRepository, cart);
        return cartDTOMapper.apply(cart);
    }

    public void addProductToCart(int cartId, int productId) {
        Cart cart = Utility.findEntityById(cartRepository, cartId)
                .orElseThrow(MissingEntityException::new);
        Product product = Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
        int numActiveSellingByProduct = getActiveCartsWithProductSell(productId);
        if (numActiveSellingByProduct > product.getStock()) {
            throw new ExceededStockException();
        }
        cart.getProductList().add(product);
        Utility.saveEntity(cartRepository, cart);
    }

    private int getActiveCartsWithProductSell(int productId) {
        int numActiveSellingByProduct = 0;
        List<Cart> listActiveCartWithProduct = cartRepository.findAllByProductListIdAndIsClosedFalse(productId);
        for (Cart toCount : listActiveCartWithProduct) {
            numActiveSellingByProduct += 1;
        }
        return numActiveSellingByProduct;
    }
}
