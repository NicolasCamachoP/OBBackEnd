package com.onebuilder.backend.entityDTO;

public class SaleItemIngressDTO {
    public String productEAN;
    public String productName;
    public Integer quantity;
    public Double currentPrice;

    public void setProductEAN(String productEAN) {
        this.productEAN = productEAN;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "SaleItemIngressDTO{" +
                "productEAN='" + productEAN + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
