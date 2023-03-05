package com.domenicozagaria.admin.util.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class UniqueIdentifierDTO {
    private UUID uuid;
}
