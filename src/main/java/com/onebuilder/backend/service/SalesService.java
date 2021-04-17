package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.exception.ClientWithoutSalesException;
import com.onebuilder.backend.exception.SaleNotFoundException;
import com.onebuilder.backend.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SalesService implements ISalesService {

    @Autowired
    private SaleRepository repo;

    @Override
    public Sale getSale(Long id) {
        /*Optional<Sale> s = repo.findById(id);
        if(s.isPresent()){
            return s.get();
        }else{
            throw new SaleNotFoundException(id);
        }*/
        return repo.findById(id).orElseGet(() -> {
            throw new SaleNotFoundException(id);
        });
    }

    @Override
    public List<Sale> getSales() {
        return repo.findAll();
    }

    @Override
    public Sale createSale(Sale newSale) {
        return repo.save(newSale);
    }

    @Override
    public List<Sale> getSalesFromClient(Long clientID) {
        return repo.findByClientUID(clientID).orElseGet(() -> new ArrayList<>());
    }

    @Override
    public Sale getLastSaleFromClient(Long clientID) {
        return repo.findFirstByClientUID_UIDOrderByDateTimeDesc(clientID)
                .orElseGet(() -> {throw new ClientWithoutSalesException(clientID); });
    }
}
