package com.domenicozagaria.admin.tag;

import com.domenicozagaria.admin.util.dto.GenericDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("tags")
public class TagController {

    private final TagService tagService;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericDTO saveTag(@RequestBody @Valid TagDTO tagDTO) {
        return tagService.saveTag(tagDTO.getName());
    }

    @PatchMapping("{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTag(@PathVariable int tagId, @RequestBody @Valid TagDTO tagDTO) {
        tagService.updateTag(tagId, tagDTO.getName());
    }

    @DeleteMapping("{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable int tagId) {
        tagService.deleteTag(tagId);
    }

    @GetMapping
    public Page<TagDTO> getTags(@RequestParam int pageNumber) {
        return tagService.getAllTags(pageNumber);
    }

    @GetMapping("{tagId}")
    public TagDTO getTagById(@PathVariable int tagId) {
        return tagService.getTagById(tagId);
    }

}
