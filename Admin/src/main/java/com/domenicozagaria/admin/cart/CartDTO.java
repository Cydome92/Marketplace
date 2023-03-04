package com.domenicozagaria.admin.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CartDTO {
    private Integer id;
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
}
