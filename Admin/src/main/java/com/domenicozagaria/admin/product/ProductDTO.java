package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProductDTO extends GenericDTO {
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
    private int stock;
}
