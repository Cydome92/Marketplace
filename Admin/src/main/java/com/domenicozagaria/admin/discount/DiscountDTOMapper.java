package com.domenicozagaria.admin.discount;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DiscountDTOMapper implements Function<Discount, DiscountDTO> {

    @Override
    public DiscountDTO apply(Discount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setUuid(discount.getId());
        discountDTO.setPercentage(discount.getPercentage());
        discountDTO.setExpirationDate(discount.getExpirationDate());
        discountDTO.setStartDate(discount.getStartDate());
        discountDTO.setUsed(discount.isUsed());
        discountDTO.setSingleUse(discount.isSingleUse());
        return discountDTO;
    }
}
