package com.onebuilder.backend.service;
import java.util.List;
import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import org.springframework.stereotype.Service;

@Service
public interface ISalesService {
    SaleIngressDTO getSale(Long id);

    List<SaleIngressDTO> getSales();

    SaleIngressDTO createSale(SaleIngressDTO newSale);

    List<SaleIngressDTO> getSalesFromClient(Long clientID);

    SaleIngressDTO getLastSaleFromClient(Long clientID);
}
