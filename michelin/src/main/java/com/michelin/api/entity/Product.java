package com.michelin.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
    
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @NotNull
    @Column(name = "administrator_id")
    private Integer administrator_id;


    public Integer getProductId() {
        return product_id;
    }
    
    public void setProductId(Integer product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }
    
    public void setProductId(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return product_id;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Integer getAdministratorId() {
        return product_id;
    }
    
    public void setAdministratorId(Integer administrator_id) {
        this.administrator_id = administrator_id;
    }
}
