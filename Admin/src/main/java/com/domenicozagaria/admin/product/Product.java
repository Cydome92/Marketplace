package com.domenicozagaria.admin.product;

import com.domenicozagaria.admin.tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.List;

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
    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    private List<Tag> tagList;

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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
