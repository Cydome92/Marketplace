package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.tag.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
public class DiscountTag implements DiscountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private BigInteger percentage;
    private LocalDateTime expiration;
    @OneToOne(fetch = FetchType.EAGER)
    private Tag tag;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public BigInteger getPercentage() {
        return percentage;
    }

    @Override
    public LocalDateTime getExpiration() {
        return expiration;
    }

    public Tag getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPercentage(BigInteger percentage) {
        this.percentage = percentage;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
