package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.CartItem;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISalesService {
    SaleIngressDTO getSale(Long id);

    List<SaleIngressDTO> getSales();

    SaleIngressDTO createSale(SaleIngressDTO newSale);

    List<SaleIngressDTO> getSalesFromClient(Long clientID);

    SaleIngressDTO getLastSaleFromClient(Long clientID);

    SaleIngressDTO createSale(List<CartItem> items);
}
