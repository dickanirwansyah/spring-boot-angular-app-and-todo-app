package com.spring.app.demoangular.controller;

import java.util.List;
import java.util.Optional;

import com.spring.app.demoangular.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.app.demoangular.entity.Category;
import com.spring.app.demoangular.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/api/category")
@CrossOrigin(origins = {"*"})
public class ControllerCategory {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<Category> list(){
		return categoryRepository.findAll();
	}

	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<Optional<Category>> getById(@PathVariable("categoryId") Long categoryId){
		return Optional.ofNullable(categoryRepository.findById(categoryId))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElseThrow(() -> new NotFoundException("categoryId not found"));
	}
	
	@PutMapping(value = "/{categoryId}")
	public Category updateCategory(@PathVariable("categoryId") Long categoryId,
								   @RequestBody Category category) {
		
		if (!categoryRepository.findById(categoryId).isPresent()){
			throw new NotFoundException("categoryId not found : "+categoryId);
		}
		return categoryRepository.findById(categoryId)
				.map(existsCategory -> {
					existsCategory.setName(category.getName());
					existsCategory.setStatus(category.isStatus());
					return categoryRepository.save(existsCategory);
				}).orElseThrow(() -> new NotFoundException("categoryId not found : "+categoryId));
	}

	@DeleteMapping(value = "/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId){
		if (categoryRepository.findById(categoryId).isPresent()){
			throw new NotFoundException("categoryId not found : "+categoryId);
		}

		return categoryRepository.findById(categoryId)
				.map(existsCategory -> {
					categoryRepository.delete(existsCategory);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new NotFoundException("categoryId not found : "+categoryId));
	}

	@PostMapping
	public ResponseEntity<Category> createProduct(@RequestBody Category category){
		Category newCategory = categoryRepository
				.save(category);
		return ResponseEntity
				.ok()
				.body(newCategory);
	}
}
