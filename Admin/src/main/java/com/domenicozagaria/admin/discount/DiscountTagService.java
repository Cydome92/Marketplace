package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.tag.Tag;
import com.domenicozagaria.admin.tag.TagRepository;
import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.dto.GenericDTO;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class DiscountTagService extends DiscountService {
    private final DiscountTagRepository discountTagRepository;
    private final TagRepository tagRepository;

    public DiscountTagService(DiscountTagRepository discountTagRepository, TagRepository tagRepository) {
        this.discountTagRepository = discountTagRepository;
        this.tagRepository = tagRepository;
    }

    public GenericDTO createDiscount(BigInteger percentage, LocalDateTime expiration, int tagId) {
        Tag tag = Utility.findEntityById(tagRepository, tagId)
                .orElseThrow(MissingEntityException::new);
        checkPercentage(percentage);
        checkExpirationDate(expiration);
        DiscountTag discountTag = new DiscountTag();
        discountTag.setPercentage(percentage);
        discountTag.setExpiration(expiration);
        discountTag.setTag(tag);
        discountTagRepository.save(discountTag);
        return genericDTOMapper.apply(discountDTOMapper.apply(discountTag));
    }
}
