package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    Product createProduct(Product p);

    Product getProductByEAN(String EAN);

    List<Product> getProducts();

    List<Product> getProductWithStock();

    void deleteProduct(Long id);

    Product updateProduct(Product p, Long id);

    void updateProductStock(String EAN);


}
