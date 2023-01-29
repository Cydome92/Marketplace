package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.tag.Tag;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
public class Product {
    @Id
    @SequenceGenerator(sequenceName = "product-sequence", name = "product-sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private LocalDateTime createdAt;
    @Nonnull
    private String name;
    @Column(columnDefinition = "integer default 0")
    private int stock;
    @ManyToMany(mappedBy = "productList")
    private List<Tag> tagList;

    /*
    TODO: implementare barCode e QRCode
     */

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

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @PrePersist
    public void setCreationDate() {
        createdAt = LocalDateTime.now(ZoneId.of("Europe/Rome"));
    }


    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

}
