package com.onebuilder.backend.entityDTO;

import com.onebuilder.backend.entity.CartItem;
import com.onebuilder.backend.entity.User;

import javax.persistence.*;
import java.util.List;

public class CartDTO {
    private Long id;
    private List<CartItemDTO> cartItems;

    public CartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
