package com.domenicozagaria.admin.util.mapper;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public  class GenericDTOMapper<T extends GenericDTO> implements Function<T, GenericDTO> {
    @Override
    public GenericDTO apply(T t) {
        GenericDTO dto = new GenericDTO();
        dto.setId(t.getId());
        return dto;
    }
}
