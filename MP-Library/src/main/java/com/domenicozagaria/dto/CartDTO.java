package com.domenicozagaria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CartDTO {
    private Integer id;
    private LocalDateTime createdAt;
    private String name;
}
