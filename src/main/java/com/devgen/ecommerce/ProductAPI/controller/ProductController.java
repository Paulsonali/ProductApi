package com.devgen.ecommerce.ProductAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devgen.ecommerce.ProductAPI.model.Category;
import com.devgen.ecommerce.ProductAPI.model.Product;
import com.devgen.ecommerce.ProductAPI.service.ProductService;


@RestController

public class ProductController {
	
	private ProductService productService;
	
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;
		
	}

	@SuppressWarnings("unused")
	@GetMapping("/api/v1/products")
	public List<Product> getAllProducts(@RequestParam (required = false) String category,
			@RequestParam(required = false) String name,
			@RequestParam(name="lower-price",required = false) Double lowerPrice,
	@RequestParam(name="higher-price",required = false) Double higherPrice)
	
	
	{
		//System.out.println("controller get all method.." +category);
		//System.out.println(lowerPrice+ "  :  " +higherPrice);
		System.out.println(name+" : " +lowerPrice+ "  :  " +higherPrice+ " : " +category);
		if(category!=null  ) {
			Category cat=Category.valueOf(category);
			return productService.searchByCategory(cat);
		}
		if(name!=null) {
		return productService.searchByProductName(name);
		}
		if(lowerPrice!=null &&higherPrice!=null) {
			return productService.searchByProductRange(lowerPrice,higherPrice);
		}
		if((name!=null) && (lowerPrice!=null &&higherPrice!=null) )  {
			
			
			
			return productService.searchWithAllParameter(name, lowerPrice, higherPrice);
		}
		
		return productService.getAllProduct();
	}
	
	@GetMapping("/api/v1/products/{id}")
	public Product getProductById(@PathVariable Long id) {
	//public Product getProductById(@PathVariable(  "id")Long count) {	
		System.out.println("controller get id method..");
		return productService.getById(id);
	}
	
	@PostMapping("/api/v1/products")
	public @ResponseBody String addProduct(@RequestBody Product product) {
		
		productService.add(product);
		return "Product is created";
	}
	
	
	@DeleteMapping("/api/v1/products/{id}")
	public @ResponseBody String deleteProduct(@PathVariable Long id) {
		
		boolean status=productService.delete(id);
		if(status) {
			return "Product deleted succesfully";
		}
		return "Product not found";
	}
	
	
	@PutMapping("/api/v1/products/{id}")
	public @ResponseBody String updateProduct(@RequestBody Product product,@PathVariable Long id) {
		product.setId(id);
		boolean status=productService.updateProduct(product);
		if(status) {
			return "Product updated succesfully";
		}
		return "Product not found";
	}
	
	@GetMapping("/api/v1/products/search/{category}")
	public @ResponseBody List<Product> searchByCategory(@PathVariable Category category) {
		
		return productService.searchByCategory(category);
	}
	
	
	@GetMapping("/api/v1/products/search/{name}")
	public @ResponseBody List<Product> searchByProductName(@PathVariable String name) {
		
		return productService.searchByProductName(name);
		
	}
	
	
	
	
	
	
	
	
	
	
}
