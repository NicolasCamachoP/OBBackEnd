package com.onebuilder.backend.rest;

import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import com.onebuilder.backend.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SalesController {

    @Autowired
    private ISalesService salesService;

    @RolesAllowed("ROLE_USER")
    @PostMapping("/create")
    SaleIngressDTO createSale(@RequestBody SaleIngressDTO newSale) {
        return salesService.createSale(newSale);
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    List<SaleIngressDTO> getAllSales() {
        return salesService.getSales();
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}")
    SaleIngressDTO getById(@PathVariable Long id) {
        return salesService.getSale(id);
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/client/all/{id}")
    List<SaleIngressDTO> getAllFromClient(@PathVariable Long id) {
        return salesService.getSalesFromClient(id);
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/client/last/{id}")
    SaleIngressDTO getLastFromClient(@PathVariable Long id) {
        return salesService.getLastSaleFromClient(id);
    }


}
