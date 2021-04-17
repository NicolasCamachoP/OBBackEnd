package com.onebuilder.backend.service;
import java.util.List;
import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import org.springframework.stereotype.Service;

@Service
public interface ISalesService {
    Sale getSale(Long id);

    List<SaleIngressDTO> getSales();

    SaleIngressDTO createSale(Sale newSale);

    List<Sale> getSalesFromClient(Long clientID);

    Sale getLastSaleFromClient(Long clientID);
}
