package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.util.dto.UniqueIdentifierDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class DiscountDTO extends UniqueIdentifierDTO {
    @PositiveOrZero
    @Max(100)
    private Double percentage;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime expirationDate;
    @NotNull
    private Boolean used;
}
