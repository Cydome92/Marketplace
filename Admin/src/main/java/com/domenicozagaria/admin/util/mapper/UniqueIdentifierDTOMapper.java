package com.domenicozagaria.admin.util.mapper;

import com.domenicozagaria.admin.util.dto.UniqueIdentifierDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UniqueIdentifierDTOMapper<T extends UniqueIdentifierDTO> implements Function<T, UniqueIdentifierDTO> {
    @Override
    public UniqueIdentifierDTO apply(T t) {
        UniqueIdentifierDTO uniqueIdentifierDTO = new UniqueIdentifierDTO();
        uniqueIdentifierDTO.setUuid(t.getUuid());
        return uniqueIdentifierDTO;
    }
}
