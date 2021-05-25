package com.onebuilder.backend.entity;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_cart")
    private Cart cart;

    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private String productEAN;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Double currentPrice;

    public String getProductEAN() {
        return productEAN;
    }

    public void setProductEAN(String productEAN) {
        this.productEAN = productEAN;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productEAN='" + productEAN + '\'' +
                ", productName='" + productName + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
