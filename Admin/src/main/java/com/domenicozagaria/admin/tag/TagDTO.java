package com.domenicozagaria.admin.tag;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TagDTO extends GenericDTO {
    @NotEmpty
    private String name;
}
