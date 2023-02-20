package com.domenicozagaria.admin.tag;

import com.domenicozagaria.admin.product.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
public class Tag {
    @Id
    @SequenceGenerator(sequenceName = "tag-sequence", name = "tag-sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
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
