package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.product.Product;
import com.domenicozagaria.admin.product.ProductRepository;
import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.dto.GenericDTO;
import com.domenicozagaria.admin.util.exception.CartClosedException;
import com.domenicozagaria.admin.util.exception.ExceededStockException;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
import com.domenicozagaria.admin.util.mapper.GenericDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDTOMapper cartDTOMapper;
    //FIXME non dovrebbe manipolare il repository, ma il service
    private final ProductRepository productRepository;
    private final GenericDTOMapper genericDTOMapper;

    public Page<CartDTO> findCartByPeriod(int pageNumber, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        Pageable pageable = Utility.getPageable(pageNumber);
        return cartRepository.findAllByCreatedAtBetween(pageable, startPeriod, endPeriod).map(cartDTOMapper);
    }

    public CartDTO findCartById(int id) {
        return Utility.findEntityById(cartRepository, id)
                .map(cartDTOMapper)
                .orElseThrow(MissingEntityException::new);
    }

    public GenericDTO createCart(String name) {
        Cart cart = new Cart();
        cart.setName(name);
        cart.setClosed(false);
        Utility.saveEntity(cartRepository, cart);
        return genericDTOMapper.apply(cartDTOMapper.apply(cart));
    }

    public void deleteCart(int cartId) {
        Cart cart = Utility.findEntityById(cartRepository, cartId)
                        .orElseThrow(MissingEntityException::new);
        if (cart.getClosed() == true) {
            throw new CartClosedException();
        }
        Utility.deleteEntity(cartRepository, cart);
    }

    public void updateCart(int cartId, boolean setClosed) {
        Cart cart = Utility.findEntityById(cartRepository, cartId)
                .orElseThrow(MissingEntityException::new);
        cart.setClosed(setClosed);
        cartRepository.save(cart);
    }

    public void addProductToCart(int cartId, int productId) {
        Cart cart = Utility.findEntityById(cartRepository, cartId)
                .orElseThrow(MissingEntityException::new);
        Product product = Utility.findEntityById(productRepository, productId)
                .orElseThrow(MissingEntityException::new);
        long numActiveSellingByProduct = getActiveCartsWithProductSell(productId);
        if (numActiveSellingByProduct > product.getStock()) {
            throw new ExceededStockException();
        }
        cart.getProductList().add(product);
        Utility.saveEntity(cartRepository, cart);
    }

    private long getActiveCartsWithProductSell(int productId) {
        List<Cart> listActiveCartWithProduct = cartRepository.findAllByProductListIdAndIsClosedFalse(productId);
        long result = listActiveCartWithProduct.stream()
                .count();
        return result;
    }
}
