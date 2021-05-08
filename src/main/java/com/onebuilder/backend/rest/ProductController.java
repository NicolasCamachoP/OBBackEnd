package com.onebuilder.backend.rest;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.entityDTO.ProductDTO;
import com.onebuilder.backend.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/create")
    ProductDTO createProduct(@RequestBody ProductDTO product) {
        return productService.createProduct(product);
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/all/{PAGE}/{SIZE}")
    Page<ProductDTO> findAllProducts(@PathVariable int page,
                                     @PathVariable int size
    ) {
        Pageable pageable = new SolrPageRequest(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<Product> products = productService.getProducts(pageable);

        List<ProductDTO> result = convertDTO(products);

        return new PageImpl(result, pageable, products.getTotalElements());
    }

    private List<ProductDTO> convertDTO(Page<Product> products) {
        List<ProductDTO> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Product product : products)
            result.add(modelMapper.map(product, ProductDTO.class));
        return result;

    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    List<ProductDTO> findAllProductsWithStock() {
        return productService.getProductWithStock();
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{ean}")
    ProductDTO getProductById(@PathVariable String ean) {
        return productService.getProductByEAN(ean);
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/update")
    ProductDTO updateProduct(@RequestBody ProductDTO product) {
        return productService.updateProduct(product);
    }

}
