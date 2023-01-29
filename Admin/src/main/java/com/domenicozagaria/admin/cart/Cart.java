package com.domenicozagaria.admin.cart;

import com.domenicozagaria.admin.product.Product;
import com.domenicozagaria.admin.util.Utility;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cart {
    @Id
    @SequenceGenerator(sequenceName = "cart-sequence", name = "cart-sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private LocalDateTime createdAt;
    private String name;
    @Column(columnDefinition = "boolean default false")
    private Boolean isClosed;

    @ManyToMany
    private List<Product> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @PrePersist
    public void setCreationDate() {
        createdAt = Utility.getTodayWithDefaultTimezone();
    }
}
