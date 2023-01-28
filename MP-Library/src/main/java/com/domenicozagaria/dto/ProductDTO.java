package com.domenicozagaria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ProductDTO {
    private Integer id;
    private LocalDateTime createdAt;
    private String name;
    private int stock;
}
