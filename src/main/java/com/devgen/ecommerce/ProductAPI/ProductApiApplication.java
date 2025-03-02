package com.devgen.ecommerce.ProductAPI;



import com.devgen.ecommerce.ProductAPI.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ProductApiApplication {

	public static void main(String[] args) {



		SpringApplication.run(ProductApiApplication.class, args);
	/*ApplicationContext context=SpringApplication.run(ProductApiApplication.class, args);
		ProductService productService=context.getBean(ProductService.class);
		productService.getAllProduct().forEach(System.out::println);*/


	}

}
