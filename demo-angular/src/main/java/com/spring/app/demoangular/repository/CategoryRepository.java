package com.spring.app.demoangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.app.demoangular.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
