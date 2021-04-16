package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.exception.ProductNotFoundException;
import com.onebuilder.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public Product createProduct(Product p) {
        return repo.save(p);
    }

    @Override
    public Product getProductByEAN(String EAN) {
        Optional<Product> p = repo.findProductByEAN(EAN);
        if (p.isPresent()) {
            return p.get();
        } else {
            throw new ProductNotFoundException(EAN);
        }
    }

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @Override
    public List<Product> getProductWithStock() {
        return repo.findByStockGreaterThan(0).get();
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> p = repo.findById(id);
        if (p.isPresent()) {
            repo.delete(p.get());
        } else {
            throw new ProductNotFoundException(id.toString());
        }

    }

    @Override
    public Product updateProduct(Product p, Long id) {
        return repo.findById(id).map(pr -> {
            pr.setEAN(p.getEAN());
            pr.setDescription(p.getDescription());
            pr.setName(p.getName());
            pr.setPrice(p.getPrice());
            pr.setStock(p.getStock());

            return repo.save(pr);
        }).orElseGet(() -> {
            throw new ProductNotFoundException(id.toString());
        });
    }

    @Override
    public void updateProductStock(String EAN) {
        Optional<Product> p = repo.findProductByEAN(EAN);
        if (p.isPresent()) {
            Product pr = p.get();
            pr.setStock(pr.getStock() - 1);
            this.updateProduct(pr, pr.getUID());
        }else{
            throw new ProductNotFoundException(EAN);
        }
    }
}
