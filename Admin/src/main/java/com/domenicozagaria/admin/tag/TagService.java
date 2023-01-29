package com.domenicozagaria.admin.tag;

import com.domenicozagaria.dto.TagDTO;
import com.domenicozagaria.exception.AlreadyInUseEntityException;

import java.util.List;

public class TagService {
    private final TagRepository tagRepository;
    private final TagDTOMapper tagDTOMapper;

    public TagService(TagRepository tagRepository, TagDTOMapper tagDTOMapper) {
        this.tagRepository = tagRepository;
        this.tagDTOMapper = tagDTOMapper;
    }

    public void saveTag(String name) {
        if (tagRepository.existsByName(name))
            throw new AlreadyInUseEntityException("Nome tag già in uso.");

        Tag tag = new Tag();
        tag.setName(name);
        tagRepository.save(tag);
    }

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(tagDTOMapper)
                .toList();
    }
}
