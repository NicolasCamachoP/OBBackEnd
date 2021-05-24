package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.Cart;
import com.onebuilder.backend.entity.CartItem;
import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.entityDTO.CartDTO;
import com.onebuilder.backend.entityDTO.CartItemDTO;
import com.onebuilder.backend.entityDTO.ProductDTO;
import com.onebuilder.backend.entityDTO.SaleIngressDTO;
import com.onebuilder.backend.exception.CartNotFoundException;
import com.onebuilder.backend.exception.StockNotEnoughException;
import com.onebuilder.backend.repository.CartItemRepository;
import com.onebuilder.backend.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository repo;
    @Autowired
    private CartItemRepository repoCI;
    @Autowired
    private UserService userService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private ProductService productService;

    @Override
    public CartDTO addProduct(ProductDTO product) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Cart> cart = repo.findByUser(userService.getUserFromCredentials(auth.getName()));
        if (cart.isPresent()) {
            int index = -1;
            for (int i = 0; i < cart.get().getCartItems().size(); i++) {
                if (cart.get().getCartItems().get(i).getProductEAN().equals(product.getEAN())) {
                    index = i;
                    break;
                }
            }
            if (index > -1) {
                int carItemQnt = cart.get().getCartItems().get(index).getQuantity();
                if (carItemQnt + 1 <= product.getStock()) {
                    cart.get().getCartItems().get(index).setQuantity(carItemQnt + 1);
                    repo.save(cart.get());
                    return createCartDTO(cart.get());
                } else {
                    throw new StockNotEnoughException("Not enough stock for: " + product.getEAN());
                }
            } else {
                CartItem newItem = new CartItem();
                newItem.setCart(cart.get());
                newItem.setProductName(product.getName());
                newItem.setProductEAN(product.getEAN());
                newItem.setQuantity(1);
                newItem.setCurrentPrice(product.getPrice());
                cart.get().getCartItems().add(newItem);
                repo.save(cart.get());
            }
            return createCartDTO(cart.get());
        } else {
            throw new CartNotFoundException(auth.getName());
        }
    }

    private CartDTO createCartDTO(Cart cart) {
        ModelMapper modelMapper = new ModelMapper();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartItems(new ArrayList<>());
        for (CartItem ci : cart.getCartItems()) {
            cartDTO.getCartItems().add(modelMapper.map(ci, CartItemDTO.class));
        }
        cartDTO.setId(cart.getId());
        return cartDTO;
    }

    @Override
    public CartDTO removeProduct(ProductDTO product) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Cart> cart = repo.findByUser(userService.getUserFromCredentials(auth.getName()));
        if (cart.isPresent()) {
            int index = -1;
            for (int i = 0; i < cart.get().getCartItems().size(); i++) {
                if (cart.get().getCartItems().get(i).getProductEAN().equals(product.getEAN())) {
                    index = i;
                    break;
                }
            }
            if (index > -1) {
                String ean =
                        cart.get().getCartItems().get(index).getProductEAN();
                int carItemQnt = cart.get().getCartItems().get(index).getQuantity();
                if (carItemQnt <= 1) {
                    cart.get().getCartItems().remove(index);
                } else {
                    cart.get().getCartItems().get(index).setQuantity(carItemQnt - 1);
                }
                repo.save(cart.get());
                repoCI.deleteByCart_IdAndAndProductEAN(cart.get().getId(),
                        ean);
                return createCartDTO(cart.get());
            }
            System.err.println("No se encontró el producto solicitado: " + product.getName() );
            return createCartDTO(cart.get());
        } else {
            throw new CartNotFoundException(auth.getName());
        }
    }

    @Override
    public SaleIngressDTO removeCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Cart> cart = repo.findByUser(userService.getUserFromCredentials(auth.getName()));
        System.err.println("Antes :" +cart.get());
        if (cart.isPresent()) {
            if (validPurchase(cart.get().getCartItems())) {
                for(CartItem ci: cart.get().getCartItems()){
                    productService.updateProductStock(ci.getProductEAN(), ci.getQuantity());
                }
                SaleIngressDTO sale = salesService.createSale(cart.get().getCartItems());
                cart.get().setCartItems(new ArrayList<>());
                repo.save(cart.get());
                repoCI.deleteAllByCart_Id(cart.get().getId());
                return sale;
            } else {
                throw new StockNotEnoughException("Alguno de los productos no tiene stock suficiente...");
            }
        } else {
            throw new CartNotFoundException(auth.getName());
        }
    }
    private boolean validPurchase(List<CartItem> cartItems) {
        boolean isValid = true;
        for(CartItem ci: cartItems){
            if (!productService.haveStock(ci.getProductEAN(), ci.getQuantity())){
                isValid = false;
                break;
            }
        }
        return isValid;
    }
    @Override
    public void createCart(User user) {
        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());
        cart.setUser(user);
        repo.save(cart);
    }

    @Override
    public CartDTO getCurrentUserCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Cart> cart = repo.findByUser(userService.getUserFromCredentials(auth.getName()));
        if (cart.isPresent()) {
            return createCartDTO(cart.get());
        } else {
            throw new CartNotFoundException(auth.getName());
        }
    }
}
