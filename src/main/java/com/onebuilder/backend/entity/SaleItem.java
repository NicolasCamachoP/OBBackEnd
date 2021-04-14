package com.onebuilder.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_sale", nullable = false, updatable = false)
    private Sale sale;

    @Column(nullable = false)
    private String productEAN;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double currentPrice;

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

	@Override
	public String toString() {
		return "SaleItem [UID=" + UID + ", productEAN=" + productEAN + ", productName=" + productName
				+ ", quantity=" + quantity + ", currentPrice=" + currentPrice + "]";
	}
}
