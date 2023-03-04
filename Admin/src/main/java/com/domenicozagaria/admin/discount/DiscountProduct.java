package com.domenicozagaria.admin.discount;

import com.domenicozagaria.admin.product.Product;
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
public class DiscountProduct implements DiscountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private BigInteger percentage;
    private LocalDateTime expiration;
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

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

    public Product getProduct() {
        return product;
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

    public void setProduct(Product product) {
        this.product = product;
    }
}
