package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.dto.UniqueIdentifierDTO;
import com.domenicozagaria.admin.util.exception.InvalidPeriodDiscountException;
import com.domenicozagaria.admin.util.mapper.UniqueIdentifierDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final UniqueIdentifierDTOMapper<DiscountDTO> uniqueIdentifierDTOMapper;
    private final DiscountDTOMapper discountDTOMapper;

    public UniqueIdentifierDTO createDiscount(LocalDateTime startDate, LocalDateTime expirationDate, Double percentage) {
        checkOverridingPeriod(discountRepository.findAll(), startDate, expirationDate);
        Discount discount = new Discount();
        discount.setPercentage(percentage);
        discount.setStartDate(startDate);
        discount.setExpirationDate(expirationDate);
        discountRepository.save(discount);
        return uniqueIdentifierDTOMapper.apply(discountDTOMapper.apply(discount));
    }

    private void checkOverridingPeriod(List<Discount> discountList, LocalDateTime startPeriod, LocalDateTime expirationDate) {
        boolean override = discountList.parallelStream()
                .anyMatch(findOverridingPeriod(startPeriod, expirationDate));
        if (override) {
            throw new InvalidPeriodDiscountException();
        }
    }

    private Predicate<Discount> findOverridingPeriod(LocalDateTime startPeriod, LocalDateTime expirationDate) {
        return discount -> Utility.checkInBetween(discount.getStartDate(), startPeriod, expirationDate)
                || Utility.checkInBetween(discount.getExpirationDate(), startPeriod, expirationDate);
    }
}
