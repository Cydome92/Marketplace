package com.domenicozagaria.admin.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProductDTO {
    private Integer id;
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
    private int stock;
}
