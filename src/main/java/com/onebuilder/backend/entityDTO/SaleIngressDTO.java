package com.onebuilder.backend.entityDTO;

import java.util.Date;
import java.util.List;

public class SaleIngressDTO {
    public Long saleID;
    public Date dateTime;
    public List<SaleItemIngressDTO> saleItems;
    public Long clientID;

    public void setSaleID(Long saleID) {
        this.saleID = saleID;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setSaleItems(List<SaleItemIngressDTO> saleItems) {
        this.saleItems = saleItems;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "SaleIngressDTO{" +
                "saleID=" + saleID +
                ", dateTime=" + dateTime +
                ", saleItems=" + saleItems +
                ", clientID=" + clientID +
                '}';
    }

}
