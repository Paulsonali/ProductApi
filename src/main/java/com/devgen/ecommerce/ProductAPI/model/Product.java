package com.devgen.ecommerce.ProductAPI.model;

import lombok.*;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Product {

    private long id;
    private  String name;
    private Category category;
    private double price;
	public Product(String name, Category category, double price) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
	}
    
    



}
