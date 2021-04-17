package com.onebuilder.backend.service;
import java.util.List;
import com.onebuilder.backend.entity.Sale;
import org.springframework.stereotype.Service;

@Service
public interface ISalesService {
    Sale getSale(Long id);

    List<Sale> getSales();

    Sale createSale(Sale newSale);

    List<Sale> getSalesFromClient(Long clientID);

    Sale getLastSaleFromClient(Long clientID);
}
