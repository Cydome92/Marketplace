package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.product.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CartDTOMapper implements Function<Cart, CartDTO> {
    @Override
    public CartDTO apply(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setName(cart.getName());
        cartDTO.setIsClosed(cart.getClosed());
        cartDTO.setCreatedAt(cart.getCreatedAt());
        cartDTO.setProductIds(
                cart.getProductList().stream()
                        .map(Product::getId)
                        .collect(Collectors.toSet())
        );
        return cartDTO;
    }
}
