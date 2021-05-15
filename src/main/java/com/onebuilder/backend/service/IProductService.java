package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.entityDTO.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    ProductDTO createProduct(ProductDTO p);

    ProductDTO getProductByEAN(String EAN);

    Page<Product> getProducts(Pageable pageable);

    List<ProductDTO> getProductWithStock();

    void deleteProduct(Long id);

    ProductDTO updateProduct(ProductDTO p);

    void updateProductStock(String EAN, int quantity);

    List<ProductDTO> getAllProducts();
}
