package com.T2207A.T2207A_Spring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 300)
    private String name;
    private String description;
    private Double price;
    private String thumbnail;

    private int qty;

    public int getQty() {
        return qty;
    }

    public Product setQty(int qty) {
        this.qty = qty;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Product setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
