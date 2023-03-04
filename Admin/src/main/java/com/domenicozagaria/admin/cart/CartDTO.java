package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CartDTO extends GenericDTO {
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
}
