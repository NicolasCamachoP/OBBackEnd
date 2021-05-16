package com.onebuilder.backend.main;

import com.onebuilder.backend.entity.*;
import com.onebuilder.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Configuration
class LoadData {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    SaleRepository saleRepository;

    @Autowired
	CartRepository cartRepository;

    @Autowired
	RoleRepository roleRepository;

	@Bean
	CommandLineRunner initDatabaseOneBuilder(BCryptPasswordEncoder bcrypt) {
		return args -> {
		    /*List<Product> products = new ArrayList<>();

			products.add(createProduct("RTX 3090",
					"La GeForce RTX™ 3090 es increíblemente potente en todas las formas, por lo que te brinda un nivel de rendimiento completamente nuevo.",
					5, 2500000, "1365489523149"));
			products.add(createProduct("RTX 3060",
					"La GeForce RTX™ 3060 Ti y la RTX 3060 te permiten disfrutar de los juegos más recientes con la potencia de Ampere, la segunda generación de la arquitectura RTX de NVIDIA.",
					5, 1500000, "1365481523149"));
			products.add(createProduct("Ryzen 5 5600X",
					"Juega con lo mejor. Seis núcleos increíbles para quienes simplemente desean jugar.",
					5, 1800000, "1364481553113"));
			products.add(createProduct("Ryzen 9 5900X",
					"El procesador que ofrece la mejor experiencia de juego del mundo. 12 núcleos para potenciar la experiencia de juego, la transmisión en vivo y mucho más.",
					5, 2300000, "2364781563111"));
			products.add(createProduct("G.SKILL Trident Z Royale 2x16",
					"Memoria RAM. Diseñada para el rendimiento, la memoria G.SKILL de escritorio se diseña con componentes elegidos y probados  rigurosamente a mano.",
					5, 900000, "2361751563222"));
			products.add(createProduct("MPG B550 Gaming Carbon WiFi",
					"La serie MPG saca lo mejor de los jugadores al permitirles la expresión máxima en color con iluminación RGB avanzada.",
					5, 900000, "2361851573222"));

			for(Product p : products)
				productRepository.save(p);

			//Roles creation
			Role r1 = new Role();
			r1.setName("ADMIN");
			Role r2 = new Role();
			r2.setName("USER");

			roleRepository.save(r1);
			roleRepository.save(r2);

			//User Creation
			User u1 = new User();
			u1.setName("Administrador");
			u1.setEmail("admin@onebuilder.com");
			u1.setPassword(bcrypt.encode("admin"));
			u1.setAdmin(true);
			u1.setRole(r1);

			User u2 = new User();
			u2.setName("Mark");
			u2.setEmail("mark@hotmail.com");
			u2.setPassword(bcrypt.encode("mark"));
			u2.setAdmin(false);
			u2.setRole(r2);

			User u3 = new User();
			u3.setName("Mateo");
			u3.setEmail("mateo@mateo.com");
			u3.setPassword(bcrypt.encode("mateo"));
			u3.setAdmin(false);
			u3.setRole(r2);

			userRepository.save(u1);
			userRepository.save(u2);
			userRepository.save(u3);

			createRandomSales(10, Arrays.asList(u2, u3),products);*/
		};
	}

	private void createRandomSales(int quantity, List<User> users, List<Product> products){
		System.out.println(users);
		Random rnd = new Random();
		for(int i = 0; i < quantity; i++){
			Sale s = new Sale();
			int pQuantity = rnd.nextInt(10) + 1;
			List<SaleItem> sil = new ArrayList<>();
			for(int j = 0; j < pQuantity; j++){
				SaleItem si = new SaleItem();
				int prod = rnd.nextInt(products.size() );
				int prodQ = rnd.nextInt(4) + 1;
				si.setSale(s);
				si.setQuantity(prodQ);
				si.setCurrentPrice(products.get(prod).getPrice());
				si.setProductEAN(products.get(prod).getEAN());
				si.setProductName(products.get(prod).getName());
				sil.add(si);
			}
			s.setSaleItems(sil);
			int randClient = rnd.nextInt(users.size());
			s.setClientUID(users.get(randClient));
			s.setDateTime(new Date());
			saleRepository.save(s);
		}

	}

	private Product createProduct(String name, String description, int stock, double price, String ean) {
		Product p = new Product();
		p.setEAN(ean);
		p.setStock(stock);
		p.setPrice(price);
		p.setName(name);
		p.setDescription(description);

		return p;
	}
}
