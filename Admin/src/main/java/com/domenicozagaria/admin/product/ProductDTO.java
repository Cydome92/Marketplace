package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProductDTO extends GenericDTO {
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
    @NotNull
    private BigDecimal price;
    private int stock;
}
