package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/create")
    Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all")
    List<Product> findAllProducts(){
        return productService.getProducts();
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping
    List<Product> findAllProductsWithStock(){
        return productService.getProductWithStock();
    }

    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/{ean}")
    Product getProductById(@PathVariable String ean){
        return productService.getProductByEAN(ean);
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/{id}")
    Product updateProduct (@RequestBody Product product, @PathVariable Long id){
        return productService.updateProduct(product, id);
    }

    @RolesAllowed("ROLE_USER")
    @PutMapping("/stock/{EAN}")
    void updateStockSale(@PathVariable Long EAN){
        productService.updateProductStock(EAN.toString());
    }


}
