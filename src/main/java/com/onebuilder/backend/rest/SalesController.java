package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@CrossOrigin
public class SalesController {

    @Autowired
    private ISalesService salesService;

    @PostMapping("/create")
    Sale createSale(@RequestBody Sale newSale) { return salesService.createSale(newSale);}

    @GetMapping("/all")
    List<Sale> getAllSales() { return salesService.getSales(); }

    @GetMapping("/{id}")
    Sale getById(@PathVariable Long id) { return salesService.getSale(id); }

    @GetMapping("/fromclient/all/{id}")
    List<Sale> getAllFromClient(@PathVariable Long id) { return salesService.getSalesFromClient(id); }

    @GetMapping("/fromclient/last/{id}")
    Sale getLastFromClient(@PathVariable Long id) { return salesService.getLastSaleFromClient(id); }


}
