package com.domenicozagaria.admin.cart;

import com.domenicozagaria.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartDTOMapper implements Function<Cart, CartDTO> {
    @Override
    public CartDTO apply(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setName(cart.getName());
        cartDTO.setCreatedAt(cart.getCreatedAt());
        return cartDTO;
    }
}
