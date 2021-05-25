package com.onebuilder.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleID;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> saleItems;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateTime;
    @ManyToOne
    private User clientUID;

    public Long getSaleID() {
        return saleID;
    }

    public void setSaleID(Long saleID) {
        this.saleID = saleID;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getClientUID() {
        return clientUID;
    }

    public void setClientUID(User clientUID) {
        this.clientUID = clientUID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sale [saleID=" + saleID + ", saleItems=");
        for (SaleItem si : saleItems) {
            sb.append(si.toString() + "\n");
        }
        sb.append(saleItems.toString() + ", dateTime=" + dateTime + ", clientUID="
                + clientUID + "]");
        return sb.toString();
    }
}
