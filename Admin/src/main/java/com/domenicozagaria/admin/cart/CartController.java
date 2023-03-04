package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.product.ProductDTO;
import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.Valid;
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

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Page<CartDTO> getCartsByPeriod(@RequestParam int page,
                                          @RequestParam LocalDateTime startPeriod,
                                          @RequestParam LocalDateTime endPeriod) {
        return cartService.findCartByPeriod(page, startPeriod, endPeriod);
    }

    @GetMapping("{cartId}")
    public CartDTO getCartById(@PathVariable int cartId) {
        return cartService.findCartById(cartId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericDTO createCart(@RequestBody @Valid CartDTO cartDTO) {
        return cartService.createCart(cartDTO.getName());
    }

    @PatchMapping("{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCart(@PathVariable int cartId, @RequestBody @Valid CartDTO cartDTO) {
        cartService.updateCart(cartId, cartDTO.getIsClosed());
    }

    @DeleteMapping("{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable int cartId) {
        cartService.deleteCart(cartId);
    }

    @PutMapping("{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProductToCart(@PathVariable int cartId, @RequestBody @Valid ProductDTO productDTO) {
        cartService.addProductToCart(cartId, productDTO.getId());
    }
}
