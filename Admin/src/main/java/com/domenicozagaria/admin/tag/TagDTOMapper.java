package com.domenicozagaria.admin.tag;

import com.domenicozagaria.dto.TagDTO;

import java.util.function.Function;

public class TagDTOMapper implements Function<Tag, TagDTO> {
    @Override
    public TagDTO apply(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        return tagDTO;
    }
}
