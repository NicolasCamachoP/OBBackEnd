package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.CartDTO;
import com.onebuilder.backend.entityDTO.ProductDTO;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICartService {
    CartDTO addProduct (ProductDTO product);
    CartDTO removeProduct(ProductDTO product);
    SaleIngressDTO removeCart ();
    void createCart(User user);
    CartDTO getCurrentUserCart();
}
