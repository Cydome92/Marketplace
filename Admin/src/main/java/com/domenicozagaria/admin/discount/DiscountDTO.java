package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class DiscountDTO extends GenericDTO {
    private BigInteger percentage;
    private LocalDateTime expiration;
}
