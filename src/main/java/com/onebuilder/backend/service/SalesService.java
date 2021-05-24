package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.CartItem;
import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.entity.SaleItem;
import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.CartItemDTO;
import com.onebuilder.backend.entityDTO.ProductDTO;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import com.onebuilder.backend.entityDTO.SaleItemIngressDTO;
import com.onebuilder.backend.exception.ClientWithoutSalesException;
import com.onebuilder.backend.exception.SaleNotFoundException;
import com.onebuilder.backend.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService implements ISalesService {

    @Autowired
    private SaleRepository repo;

    @Autowired
    private UserService userService;

    @Override
    public SaleIngressDTO getSale(Long id) {
        SaleIngressDTO saleDTO = new SaleIngressDTO();
        Sale sale;
        sale = repo.findById(id).orElseGet(() -> {
            throw new SaleNotFoundException(id);
        });
        saleDTO.setSaleID(sale.getSaleID());
        saleDTO.setClientID(sale.getClientUID().getUID());
        saleDTO.setDateTime(sale.getDateTime());
        ModelMapper modelMapper = new ModelMapper();
        List<SaleItemIngressDTO> saleItems = new ArrayList<>();
        for(SaleItem si: sale.getSaleItems()){
            saleItems.add(modelMapper.map(si, SaleItemIngressDTO.class));
        }
        saleDTO.setSaleItems(saleItems);
        return saleDTO;
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
    public SaleIngressDTO createSale(SaleIngressDTO newSale) {
        //TODO disminuir producto
        Sale s = new Sale();
        User u = userService.getUserById(newSale.clientID);
        List<SaleItem> siList = new ArrayList<>();
        for(SaleItemIngressDTO si : newSale.saleItems){
            SaleItem siParsed = new SaleItem();
            siParsed.setCurrentPrice(si.currentPrice);
            siParsed.setProductEAN(si.productEAN);
            siParsed.setQuantity(si.quantity);
            siParsed.setProductName(si.productName);
            siParsed.setSale(s);
            siList.add(siParsed);
        }
        s.setSaleItems(siList);
        s.setDateTime(new Date());
        s.setClientUID(u);
        repo.save(s);
        return newSale;
    }

    @Override
    public List<SaleIngressDTO> getSalesFromClient(Long clientID) {
        User u = userService.getUserById(clientID);
        List<Sale> sales = repo.findByClientUID(clientID).orElseGet(() -> new ArrayList<>());
        List<SaleIngressDTO> salesDTO = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Sale sale: sales){
            List<SaleItemIngressDTO>salesIDTO = new ArrayList<>();
            SaleIngressDTO s = new SaleIngressDTO();
            s.setClientID(u.getUID());
            s.setDateTime(sale.getDateTime());
            s.setSaleID(sale.getSaleID());
            for(SaleItem si: sale.getSaleItems()){
                salesIDTO.add(modelMapper.map(si, SaleItemIngressDTO.class));
            }
            s.setSaleItems(salesIDTO);
            salesDTO.add(s);
        }
        return salesDTO;
    }

    @Override
    public SaleIngressDTO getLastSaleFromClient(Long clientID) {
        User u = userService.getUserById(clientID);
        ModelMapper modelMapper = new ModelMapper();
        SaleIngressDTO saleDTO = new SaleIngressDTO();
        Sale sale = repo.findFirstByClientUID_UIDOrderByDateTimeDesc(clientID)
                .orElseGet(() -> {throw new ClientWithoutSalesException(clientID); });
        List<SaleItemIngressDTO>salesIDTO = new ArrayList<>();
        saleDTO.setClientID(u.getUID());
        saleDTO.setDateTime(sale.getDateTime());
        saleDTO.setSaleID(sale.getSaleID());
        for(SaleItem si: sale.getSaleItems()){
            salesIDTO.add(modelMapper.map(si, SaleItemIngressDTO.class));
        }
        saleDTO.setSaleItems(salesIDTO);
        return saleDTO;
    }

    @Override
    public SaleIngressDTO createSale(List<CartItem> items) {
        ModelMapper modelMapper = new ModelMapper();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.err.println(auth.getName());
        Sale newSale = new Sale();
        newSale.setClientUID(userService.getUserFromCredentials(auth.getName()));
        newSale.setDateTime(new Date());
        List<SaleItem> saleItems = new ArrayList<>();
        for(CartItem ci: items){
            SaleItem si = new SaleItem();
            si.setSale(newSale);
            si.setCurrentPrice(ci.getCurrentPrice());
            si.setProductEAN(ci.getProductEAN());
            si.setProductName(ci.getProductName());
            si.setQuantity(ci.getQuantity());
            saleItems.add(si);
        }
        newSale.setSaleItems(saleItems);
        newSale = repo.save(newSale);
        SaleIngressDTO saleDTO = new SaleIngressDTO();
        saleDTO.setClientID(newSale.getClientUID().getUID());
        saleDTO.setDateTime(newSale.getDateTime());
        saleDTO.setSaleID(newSale.getSaleID());
        List<SaleItemIngressDTO> salesIDTO = new ArrayList<>();
        for(SaleItem si: newSale.getSaleItems()){
            salesIDTO.add(modelMapper.map(si, SaleItemIngressDTO.class));
        }
        saleDTO.setSaleItems(salesIDTO);
        return saleDTO;
    }
}
