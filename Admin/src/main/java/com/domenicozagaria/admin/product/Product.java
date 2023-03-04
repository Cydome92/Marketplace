package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.cart.Cart;
import com.domenicozagaria.admin.tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @SequenceGenerator(sequenceName = "product-sequence", name = "product-sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String name;
    @Column(columnDefinition = "integer default 0")
    private int stock;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Tag> tagList;

    //TODO: implementare barCode e QRCode


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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(Set<Tag> tagList) {
        this.tagList = tagList;
    }
}
