package com.domenicozagaria.admin.discount;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DiscountDTOMapper implements Function<DiscountModel, DiscountDTO> {
    @Override
    public DiscountDTO apply(DiscountModel discountModel) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(discountModel.getId());
        discountDTO.setPercentage(discountModel.getPercentage());
        discountDTO.setExpiration(discountModel.getExpiration());
        return discountDTO;
    }
}
