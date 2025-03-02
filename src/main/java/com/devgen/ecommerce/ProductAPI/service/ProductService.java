package com.devgen.ecommerce.ProductAPI.service;

import com.devgen.ecommerce.ProductAPI.model.Category;
import com.devgen.ecommerce.ProductAPI.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductService {

	protected Map<Long, Product> productMap;
	private Long id;

	public ProductService() {
		productMap = new HashMap<>();
		this.id = 1L;
		initilizeProduct();
	}

	private void initilizeProduct() {
		add(new Product("Laptop", Category.ELECTRONICS, 1000.00));
		add(new Product("Laptop", Category.ELECTRONICS, 500.00));
		add(new Product("Laptop", Category.ELECTRONICS, 700.00));
		add(new Product("T-Shirt", Category.CLOTHING, 20.00));
		add(new Product("History", Category.BOOKS, 100.00));
		add(new Product("History", Category.BOOKS, 50.00));
		add(new Product("History", Category.BOOKS, 20.00));
		add(new Product("Table", Category.FURNITURE, 200.00));
		add(new Product("Desktop", Category.ELECTRONICS, 500.00));
		add(new Product("Shirt", Category.CLOTHING, 20.00));
		add(new Product("Glasses", Category.CLOTHING, 20.00));
		add(new Product("SunGlasses", Category.CLOTHING, 20.00));
		add(new Product("Biography", Category.BOOKS, 10.00));
		add(new Product("Sofa", Category.FURNITURE, 200.00));
		add(new Product("Tablet", Category.ELECTRONICS, 1000.00));
		add(new Product("Trouser", Category.CLOTHING, 20.00));
		add(new Product("Science-Fiction", Category.BOOKS, 50.00));
		add(new Product("Chair", Category.FURNITURE, 200.00));
		add(new Product("Mobile", Category.ELECTRONICS, 1000.00));

	}

	public void add(Product product) {
		product.setId(id);
		productMap.put(id, product);
		id++;

	}

	public Product getById(Long id) {
		return productMap.get(id);
	}

	public List<Product> getAllProduct() {
		System.out.println("service get all method..");
		return new ArrayList<>(productMap.values());
	}

	public boolean delete(Long id) {
		return productMap.remove(id) != null;
	}

	public  List<Product> searchByCategory(Category category) {
		List<Product> matchingProduct = new ArrayList<>();
		for (Product product : productMap.values()) {
			if (product.getCategory().equals(category))
				
			matchingProduct.add(product);
		}
		return matchingProduct;

	}
	


	public boolean updateProduct(Product newproduct) {
		Product exsistingProduct=productMap.get(newproduct.getId());
		if(exsistingProduct!=null) {
			exsistingProduct.setName(newproduct.getName());
			exsistingProduct.setPrice(newproduct.getPrice());
			return true;
		}
		
		return false;
	}

	public List<Product> searchByProductName(String name) {
		/*List<Product> matchingProduct = new ArrayList<>();
		for (Product product : productMap.values()) {
			if (product.getCategory().equals(name))
				
			matchingProduct.add(product);
		}
		return matchingProduct;*/
		
		/*return productMap.values().stream().
				filter((Product product)->product.getName()
						.toLowerCase().contains(name.toLowerCase())).toList();*/
		return productMap.values().stream().
				filter((Product product)->isNameMatching(name, product)).toList();
		
		
	}
	
	private static boolean isNameMatching(String name,Product product) {
		return product.getName().toLowerCase().contains(name.toLowerCase());	
	}

	public List<Product> searchByProductRange(Double lowerPrice, Double higherPrice) {
		
		
		List<Product> matchingProduct= productMap.values().stream().
				filter((Product product)->isPriceRangeValid(lowerPrice,higherPrice, product)).
				sorted(Comparator.comparingDouble(Product::getPrice))
				.collect(Collectors.toList());
		
		//List<Product> allProduct=new ArrayList<>(matchingProduct);
		
		//Collections.sort(allProduct,(Product p1,Product p2)->Double.compare(p1.getPrice(),p2.getPrice()));
		return matchingProduct;
		
	}

	private boolean isPriceRangeValid(Double lowerPrice, Double higherPrice, Product product) {
		
		
		
		return product.getPrice()>=lowerPrice && product.getPrice()<=higherPrice;
	}
	
	public List<Product> searchWithAllParameter(String name,
			Double lowerPrice,Double higherPrice){
		
		
		List<Product> matchingProducts= productMap.values().stream().filter( (Product product)->isNameMatching(name, product)).
				
				filter((Product product)->isPriceRangeValid(lowerPrice,higherPrice, product)).
				sorted(Comparator.comparingDouble(Product::getPrice)).
				
			
				collect(Collectors.toList());
		return matchingProducts;
	
	}
	
	
}
