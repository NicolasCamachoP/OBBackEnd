package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/all")
    List<Product> findAllProducts(){
        return productService.getProducts();
    }

    @GetMapping
    List<Product> findAllProductsWithStock(){
        return productService.getProductWithStock();
    }

    @GetMapping("/{EAN}")
    Product getProductById(@PathVariable Long EAN){
        return productService.getProductByEAN(EAN.toString());
    }

    @DeleteMapping("/delete/{EAN}")
    void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    Product updateProduct (@RequestBody Product product, @PathVariable Long id){
        return productService.updateProduct(product, id);
    }

    @PutMapping("/stock/{EAN}")
    void updateStockSale(@PathVariable Long EAN){
        productService.updateProductStock(EAN.toString());
    }


}
