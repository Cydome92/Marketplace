package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.dto.UniqueIdentifierDTO;
import com.domenicozagaria.admin.util.exception.InvalidPeriodDiscountException;
import com.domenicozagaria.admin.util.exception.MissingEntityException;
import com.domenicozagaria.admin.util.mapper.UniqueIdentifierDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final UniqueIdentifierDTOMapper<DiscountDTO> uniqueIdentifierDTOMapper;
    private final DiscountDTOMapper discountDTOMapper;

    public UniqueIdentifierDTO createDiscount(LocalDateTime startDate, LocalDateTime expirationDate, Double percentage,
                                              boolean isUsed, boolean singleUse) {
        checkOverridingPeriod(discountRepository.findAll(), startDate, expirationDate);
        Discount discount = new Discount();
        populateDiscount(startDate, expirationDate, percentage, isUsed, singleUse, discount);
        discountRepository.save(discount);
        return uniqueIdentifierDTOMapper.apply(discountDTOMapper.apply(discount));
    }

    public void deleteDiscount(UUID discountId) {
        Discount discount = Utility.findEntityById(discountRepository, discountId)
                        .orElseThrow(MissingEntityException::new);
        discountRepository.delete(discount);
    }

    public void updateDiscount(UUID discountId, LocalDateTime startDate, LocalDateTime expirationDate,
                                Double percentage, boolean isUsed, boolean singleUse) {
        Discount discount = Utility.findEntityById(discountRepository, discountId)
                .orElseThrow(MissingEntityException::new);
        List<Discount> listAllDiscountsMinusSelf = discountRepository.findByIdNot(discount.getId());
        checkOverridingPeriod(listAllDiscountsMinusSelf, startDate, expirationDate);
        populateDiscount(startDate, expirationDate, percentage, isUsed, singleUse, discount);
        discountRepository.save(discount);
    }

    public Optional<Discount> getActualMaxDiscount(Set<Discount> discountSet) {
        return discountSet.stream()
                .filter(d -> !d.isUsed())
                .filter(d -> Utility.checkInBetween(Utility.getTodayWithDefaultTimezone(), d.getStartDate(), d.getExpirationDate()))
                .max(Comparator.comparing(Discount::getPercentage));
    }

    public BigDecimal evaluateDiscount(BigDecimal discount, BigDecimal originalPrice) {
        return discount.divide(BigDecimal.valueOf(100), RoundingMode.DOWN).multiply(originalPrice);
    }

    private void populateDiscount(LocalDateTime startDate, LocalDateTime expirationDate, Double percentage,
                                  boolean isUsed, boolean singleUse, Discount discount) {
        discount.setPercentage(percentage);
        discount.setStartDate(startDate);
        discount.setExpirationDate(expirationDate);
        discount.setUsed(isUsed);
        discount.setSingleUse(singleUse);
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
