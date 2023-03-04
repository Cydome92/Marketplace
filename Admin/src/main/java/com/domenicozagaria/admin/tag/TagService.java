package com.domenicozagaria.admin.tag;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.dto.GenericDTO;
import com.domenicozagaria.admin.util.exception.AlreadyInUseEntityException;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
import com.domenicozagaria.admin.util.mapper.GenericDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagDTOMapper tagDTOMapper;
    private final GenericDTOMapper genericDTOMapper;

    public GenericDTO saveTag(String name) {
        checkNameTag(name);
        Tag tag = new Tag();
        tag.setName(name);
        Utility.saveEntity(tagRepository, tag);
        return genericDTOMapper.apply(tagDTOMapper.apply(tag));
    }

    public void updateTag(int tagId, String name) {
        Tag tag = Utility.findEntityById(tagRepository, tagId)
                .orElseThrow(MissingEntityException::new);
        checkNameTag(name);
        tag.setName(name);
        tagRepository.save(tag);
    }

    public void deleteTag(int tagId) {
        Tag toDelete = Utility.findEntityById(tagRepository, tagId)
                .orElseThrow(MissingEntityException::new);
        Utility.deleteEntity(tagRepository, toDelete);
    }

    public Page<TagDTO> getAllTags(int pageNumber) {
        Pageable page = Utility.getPageable(pageNumber);
        return tagRepository.findAll(page).map(tagDTOMapper);
    }

    public TagDTO getTagById(int id) {
        return Utility.findEntityById(tagRepository, id)
                .map(tagDTOMapper)
                .orElseThrow(MissingEntityException::new);
    }

    private void checkNameTag(String name) {
        if (tagRepository.existsByName(name)) {
            throw new AlreadyInUseEntityException("Nome tag già in uso.");
        }
    }
}
