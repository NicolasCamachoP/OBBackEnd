package com.onebuilder.backend;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.repository.ProductRepository;
import com.onebuilder.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
class LoadData {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Bean
    CommandLineRunner initDatabaseOneBuilder() {
        return args -> {
          Product p1 = new Product();
          p1.setEAN("abc");
          p1.setName("RTX 3060");
          p1.setPrice(123123.0);
          p1.setDescription("Una gpu?");
          p1.setStock(123);
            Product p2 = new Product();
            p2.setEAN("abc");
            p2.setName("RTX 3070");
            p2.setPrice(123123.0);
            p2.setDescription("Una gpu?");
            p2.setStock(0);

            productRepository.save(p1);
            productRepository.save(p2);

            User u1 = new User();
            u1.setName("Mateo");
            u1.setEmail("mateo@mateo.com");
            u1.setPassword("test123");
            u1.setAdmin(true);
            u1.setToken("TODO");

            User u2 = new User();
            u2.setName("Nonito");
            u2.setEmail("nonito@nonito.com");
            u2.setPassword("test123");
            u2.setAdmin(false);
            u2.setToken("TODO1");

            userRepository.save(u1);
            userRepository.save(u2);

            List<User> users = userRepository.findAll();
            System.out.println(users);

            Optional<List<Product>> products = productRepository.findByStockGreaterThan(0);
            List<Product> productList = products.get();
            System.out.println(productList);

        };
    }
}
