package com.domenicozagaria.admin.tag;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class TagDTO extends GenericDTO {
    @NotEmpty
    private String name;
}
