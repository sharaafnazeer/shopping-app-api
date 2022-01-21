package com.creativelabs.shoppingapi;

import com.creativelabs.shoppingapi.entities.Category;
import com.creativelabs.shoppingapi.entities.Product;
import com.creativelabs.shoppingapi.entities.Role;
import com.creativelabs.shoppingapi.entities.User;
import com.creativelabs.shoppingapi.models.ERole;
import com.creativelabs.shoppingapi.repositories.CategoryRepository;
import com.creativelabs.shoppingapi.repositories.ProductRepository;
import com.creativelabs.shoppingapi.repositories.RoleRepository;
import com.creativelabs.shoppingapi.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableJpaRepositories("com.creativelabs.shoppingapi.repositories")
public class ShoppingapiApplication {

	private final CategoryRepository categoryRepository;
	private final RoleRepository roleRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	public ShoppingapiApplication(CategoryRepository categoryRepository, RoleRepository roleRepository, ProductRepository productRepository, UserRepository userRepository) {
		this.categoryRepository = categoryRepository;
		this.roleRepository = roleRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;


		this.roleRepository.save(new Role(ERole.ROLE_USER));

		this.categoryRepository.saveAll(categories);
		this.productRepository.saveAll(products);

		this.userRepository.save(new User("Sharaaf", "Nazeer",
				"ahamedsharaaf@gmail.com", "$2a$10$1Cz0M3DOvDhkrwq0m0DzauA70m/eXrtRZjAKlqRaKXiCryEN2tb.i"));

	}

	List<Category> categories = new ArrayList<>();

	{
		for (int i = 1; i <= 5; i++) {
			this.categories.add(new Category(i, "Category " + i, "", ""));
		}
	}

	List<Product> products = new ArrayList<>();

	{
		for (int i = 1; i <= 30; i++) {
			int[] array = {1, 2, 3, 4, 5};
			String[] images = {
					"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone11-select-2019-family?wid=882&hei=1058&fmt=jpeg&qlt=80&.v=1567022175704",
					"https://www.notebookcheck.net/uploads/tx_nbc2/4_to_3_Teaser_Apple_iPhone_13_Pro.jpg",
					"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/macbook-air-gold-select-201810?wid=904&hei=840&fmt=jpeg&qlt=80&.v=1633027804000",
					"https://www.cnet.com/a/img/At7JXOb2erGg-eOdKylQhFKfeJY=/2021/10/23/80425069-0d3e-4c67-9085-a66e6177fc60/macbook-pro-2021-cnet-review-12.jpg",
					"https://www.singersl.com/sites/default/files/images/products/2020-01/SIN_FAN-DFP500T-01_zoom.jpg",
					"https://m.media-amazon.com/images/I/81M4KUrL5DL._AC_SX450_.jpg",
					"https://www.jaffnamobile.com/wp-content/uploads/2020/12/galaxy-note20_highlights_kv_video_end.jpg",
					"https://images.custommade.com/J7tmys8oDWWGEYfdkkOv5xSvguc=/custommade-photosets/df0fd5f3ee723fc_low_45.jpg",
					"https://image.shutterstock.com/image-photo/fresh-green-ladies-fingers-vegitable-260nw-1767810809.jpg",
					"https://image.freepik.com/free-photo/colorful-fruits-tasty-fresh-ripe-juicy-white-desk_179666-169.jpg"
			};
			int rnd = new Random().nextInt(array.length);
			products.add(new Product(i, "XXX-" + i, "Product " + i, "",
					(int) (Math.random() * 10000), images[rnd], array[rnd]));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingapiApplication.class, args);
	}

}
