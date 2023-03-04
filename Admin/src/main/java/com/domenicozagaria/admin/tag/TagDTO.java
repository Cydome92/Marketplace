package com.domenicozagaria.admin.tag;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class TagDTO {
    private Integer id;
    @NotEmpty
    private String name;
}
