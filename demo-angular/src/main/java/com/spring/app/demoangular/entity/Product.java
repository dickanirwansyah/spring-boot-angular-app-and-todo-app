package com.spring.app.demoangular.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.spring.app.demoangular.validation.CategoryIdValidation;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	/**
	@Lob
	@Column(name = "image")
	private byte[] image;
	 **/
	
	@Column(name = "price", nullable = false)
	private Long price;
	
	@Max(10)
	@Min(1)
	@Column(name = "stock", nullable = false)
	private Integer stock;

	public Product(){
		super();
	}

	public Product(Long id, String name, Category category, Long price, Integer stock){
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stock = stock;
	}
}
