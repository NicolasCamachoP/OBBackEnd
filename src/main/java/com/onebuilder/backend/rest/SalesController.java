package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.entity.SaleItem;
import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import com.onebuilder.backend.entityDTO.SaleItemIngressDTO;
import com.onebuilder.backend.service.ISalesService;
import com.onebuilder.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SalesController {

    @Autowired
    private ISalesService salesService;
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    SaleIngressDTO createSale(@RequestBody SaleIngressDTO newSale) {
        /*System.out.println(newSale);
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
        return salesService.createSale(s);*/
        return null;
    }

    @GetMapping("/all")
    List<SaleIngressDTO> getAllSales() {
        return salesService.getSales();
    }

    @GetMapping("/{id}")
    Sale getById(@PathVariable Long id) { return salesService.getSale(id); }

    @GetMapping("/fromclient/all/{id}")
    List<Sale> getAllFromClient(@PathVariable Long id) { return salesService.getSalesFromClient(id); }

    @GetMapping("/fromclient/last/{id}")
    Sale getLastFromClient(@PathVariable Long id) { return salesService.getLastSaleFromClient(id); }


}
