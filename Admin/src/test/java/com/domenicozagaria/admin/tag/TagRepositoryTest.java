package com.domenicozagaria.admin.tag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void init() {
        Tag tag = new Tag();
        tag.setName("test-unit");
        tagRepository.save(tag);
    }

    @Test
    void existsByName() {
        Assertions.assertTrue(tagRepository.existsByName("test-unit"));
    }
}