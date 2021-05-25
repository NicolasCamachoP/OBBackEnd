package com.onebuilder.backend.rest;

import com.onebuilder.backend.entityDTO.CartDTO;
import com.onebuilder.backend.entityDTO.ProductDTO;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import com.onebuilder.backend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @RolesAllowed("ROLE_USER")
    @PostMapping("/add")
    CartDTO addProduct(@RequestBody ProductDTO product) {
        return cartService.addProduct(product);
    }

    @RolesAllowed("ROLE_USER")
    @PostMapping("/remove")
    CartDTO removeProduct(@RequestBody ProductDTO product) {
        return cartService.removeProduct(product);
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping
    CartDTO getCurrentUserCart() {
        return cartService.getCurrentUserCart();
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/checkout")
    SaleIngressDTO checkOutCart() {
        return cartService.removeCart();
    }


}
