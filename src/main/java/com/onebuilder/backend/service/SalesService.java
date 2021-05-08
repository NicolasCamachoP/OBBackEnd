package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.entity.SaleItem;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import com.onebuilder.backend.entityDTO.SaleItemIngressDTO;
import com.onebuilder.backend.exception.ClientWithoutSalesException;
import com.onebuilder.backend.exception.SaleNotFoundException;
import com.onebuilder.backend.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public List<SaleIngressDTO> getSales() {
        List<Sale> sales = repo.findAll();
        List<SaleIngressDTO> siList = new ArrayList<>();
        for( Sale s : sales){
            SaleIngressDTO sid = new SaleIngressDTO();
            sid.setDateTime(s.getDateTime());
            sid.setSaleID(s.getSaleID());
            sid.setClientID(s.getClientUID().getUID());
            List<SaleItemIngressDTO> sidl = new ArrayList<>();
            for(SaleItem si: s.getSaleItems()){
                SaleItemIngressDTO saleItem = new SaleItemIngressDTO();
                saleItem.setProductEAN(si.getProductEAN());
                saleItem.setCurrentPrice(si.getCurrentPrice());
                saleItem.setProductName(si.getProductName());
                saleItem.setQuantity(si.getQuantity());
                sidl.add(saleItem);
            }
            sid.setSaleItems(sidl);
            siList.add(sid);
        }
        System.out.println(siList);
        return siList;
    }

    @Override
    public SaleIngressDTO createSale(Sale newSale) {
        Sale s = repo.save(newSale);
        System.out.println(s);
        ModelMapper mo = new ModelMapper();
        SaleIngressDTO si = mo.map(s, SaleIngressDTO.class);
        System.out.println(si);
        return si;
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
