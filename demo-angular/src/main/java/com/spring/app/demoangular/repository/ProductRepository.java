package com.spring.app.demoangular.repository;

import com.spring.app.demoangular.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
