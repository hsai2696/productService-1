package com.productservice.products.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseModel{

    private String descr;
    private String image;
    private double price;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
