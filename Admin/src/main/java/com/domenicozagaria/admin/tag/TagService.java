package com.domenicozagaria.admin.tag;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.exception.AlreadyInUseEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagDTOMapper tagDTOMapper;

    public void saveTag(String name) {
        if (tagRepository.existsByName(name))
            throw new AlreadyInUseEntityException("Nome tag già in uso.");

        Tag tag = new Tag();
        tag.setName(name);
        Utility.saveEntity(tagRepository, tag);
    }

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(tagDTOMapper)
                .toList();
    }

    public Set<TagDTO> findAllTagsBySetIds(Set<Integer> ids) {
        return tagRepository.findByIdIn(ids)
                .stream()
                .map(tagDTOMapper)
                .collect(Collectors.toSet());
    }
}
