package com.domenicozagaria.admin.discount;

import java.math.BigInteger;
import java.time.LocalDateTime;

public interface DiscountModel {
    Integer getId();
    BigInteger getPercentage();
    LocalDateTime getExpiration();
}
