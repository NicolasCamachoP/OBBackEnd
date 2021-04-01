package com.onebuilder.backend;

import com.onebuilder.backend.entity.Product;
import com.onebuilder.backend.entity.Sale;
import com.onebuilder.backend.entity.SaleItem;
import com.onebuilder.backend.entity.User;
import com.onebuilder.backend.repository.ProductRepository;
import com.onebuilder.backend.repository.SaleRepository;
import com.onebuilder.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Configuration
class LoadData {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    SaleRepository saleRepository;

	@Bean
	CommandLineRunner initDatabaseOneBuilder() {
		return args -> {
			//Product's Creation
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
			
			//User Creation
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
			
			//Sale creation
			Sale s1 = new Sale();
			s1.setClientUID(u2);
			s1.setDateTime(new Date());
			//Sale item Creation
			SaleItem s11 = new SaleItem();
			s11.setCurrentPrice(p1.getPrice());
			s11.setProductEAN(p1.getEAN());
			s11.setProductName(p1.getName());
			s11.setQuantity(1);
			s11.setSale(s1);
			s1.setSaleItems(new ArrayList<SaleItem>() {{add(s11);}});
			
			saleRepository.save(s1);
			
			

			List<User> users = userRepository.findAll();
			System.out.println(users);

			Optional<List<Product>> products = productRepository.findByStockGreaterThan(0);
			List<Product> productList = products.get();
			System.out.println(productList);
			
			List<Sale> sales = saleRepository.findAll();
			System.out.println(sales);

		};
	}
}
