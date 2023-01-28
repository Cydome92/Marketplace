package com.domenicozagaria.admin.tag;

import com.domenicozagaria.admin.product.Product;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

import java.util.List;

@Entity
public class Tag {
    @Id
    @SequenceGenerator(sequenceName = "tag-sequence", name = "tag-sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Nonnull
    private String name;
    @ManyToMany
    private List<Product> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
