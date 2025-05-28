package com.apparel.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
public class Design {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String color;
    private String brand;
    private String material;
    private BigDecimal price;


    @OneToMany(mappedBy="design",fetch=FetchType.LAZY,orphanRemoval=true,cascade=CascadeType.ALL)
    private List<Order> orders;


    public Design(String name, String color, String brand, String material, BigDecimal price, List<Order> orders)
    {
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.material = material;
        this.price = price;
        this.orders = orders;
    }

    public Design() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}