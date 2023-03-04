package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.util.Utility;
import com.domenicozagaria.admin.util.exception.InvalidExpirationDateException;
import com.domenicozagaria.admin.util.exception.InvalidPercentageException;
import com.domenicozagaria.admin.util.mapper.GenericDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public abstract class DiscountService {

    @Autowired
    GenericDTOMapper<DiscountDTO> genericDTOMapper;
    @Autowired
    DiscountDTOMapper discountDTOMapper;

    public void checkExpirationDate(LocalDateTime expiration) {
        if (expiration.isBefore(Utility.getTodayWithDefaultTimezone())) {
            throw new InvalidExpirationDateException();
        }
    }

    public void checkPercentage(BigInteger percentage) {
        if (percentage.signum() == -1 && percentage.compareTo(BigInteger.valueOf(100)) > 0) {
            throw new InvalidPercentageException();
        }
    }
}
