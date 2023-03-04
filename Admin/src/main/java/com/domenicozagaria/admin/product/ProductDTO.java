package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProductDTO extends GenericDTO {
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
    private int stock;
}
