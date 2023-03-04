package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CartDTO extends GenericDTO {
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;
}
