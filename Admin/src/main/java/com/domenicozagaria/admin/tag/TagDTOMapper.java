package com.domenicozagaria.admin.tag;

import com.domenicozagaria.dto.TagDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TagDTOMapper implements Function<Tag, TagDTO> {
    @Override
    public TagDTO apply(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        return tagDTO;
    }
}
