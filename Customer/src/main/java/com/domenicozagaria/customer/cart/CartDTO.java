package com.domenicozagaria.customer.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CartDTO {
    private Integer id;
    private LocalDateTime createdAt;
}
