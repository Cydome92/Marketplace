package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Data
public class CartDTO extends GenericDTO {
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
    @NotNull
    private Boolean isClosed;
    private Set<Integer> productIds;
}
