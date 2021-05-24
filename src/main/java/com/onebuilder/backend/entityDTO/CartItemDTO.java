package com.onebuilder.backend.entityDTO;

import com.onebuilder.backend.entity.Cart;

import javax.persistence.*;

public class CartItemDTO {
    private Long id;
    private Integer quantity;
    private String productEAN;
    private String productName;
    private Double currentPrice;

    public CartItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductEAN() {
        return productEAN;
    }

    public void setProductEAN(String productEAN) {
        this.productEAN = productEAN;
    }

    public String getProductName() {
        return productName;
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
}
