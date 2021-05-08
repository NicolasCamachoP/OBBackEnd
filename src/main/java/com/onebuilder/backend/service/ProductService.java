package com.onebuilder.backend.service;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.entityDTO.ProductDTO;
import com.onebuilder.backend.exception.ProductAlreadyRegisteredException;
import com.onebuilder.backend.exception.ProductNotFoundException;
import com.onebuilder.backend.exception.ProductNotValidException;
import com.onebuilder.backend.exception.StockNotEnoughException;
import com.onebuilder.backend.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public ProductDTO createProduct(ProductDTO p) {
        ModelMapper modelMapper = new ModelMapper();
        if (!repo.findProductByEAN(p.getEAN()).isPresent()) {
            try {
                return modelMapper.map(repo.save(modelMapper.map(p, Product.class)), ProductDTO.class);
            } catch (Exception e) {
                throw new ProductNotValidException("Not Valid Product");
            }
        } else {
            throw new ProductAlreadyRegisteredException("Already registered " + p.getEAN());
        }
    }

    @Override
    public ProductDTO getProductByEAN(String EAN) {
        Optional<Product> p = repo.findProductByEAN(EAN);
        if (p.isPresent()) {
            return new ModelMapper().map(p.get(), ProductDTO.class);
        } else {
            throw new ProductNotFoundException(EAN);
        }
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        /*
        List<Product> products = repo.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return products.stream().map(
                product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
*/
        return repo.findAll(pageable);
    }

    @Override
    public List<ProductDTO> getProductWithStock() {
        List<Product> products = repo.findByStockGreaterThan(0).get();
        ModelMapper modelMapper = new ModelMapper();
        return products.stream().map(
                product -> modelMapper.map(product, ProductDTO.class)
        ).collect(Collectors.toList());
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
    public ProductDTO updateProduct(ProductDTO p) {
        if (!repo.findById(p.getUID()).isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(repo.save(modelMapper.map(p, Product.class)), ProductDTO.class);
        } else {
            throw new ProductNotFoundException("Product Not Found");
        }
    }

    @Override
    public void updateProductStock(String EAN, int quantity) {
        Optional<Product> p = repo.findProductByEAN(EAN);
        if (p.isPresent()) {
            Product pr = p.get();
            if (pr.getStock() >= quantity) {
                pr.setStock(pr.getStock() - quantity);
                repo.save(pr);
            } else {
                throw new StockNotEnoughException("Quantity exceeds stock");
            }
        } else {
            throw new ProductNotFoundException(EAN);
        }
    }
}
