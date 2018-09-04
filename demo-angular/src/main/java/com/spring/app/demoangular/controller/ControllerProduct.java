package com.spring.app.demoangular.controller;

import com.spring.app.demoangular.entity.Product;
import com.spring.app.demoangular.exception.NotFoundException;
import com.spring.app.demoangular.repository.CategoryRepository;
import com.spring.app.demoangular.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
@CrossOrigin(value = {"*"})
public class ControllerProduct {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Product> getListProduct(){
        return productRepository.findAll();
    }

    @PostMapping(value = "/{categoryId}")
    public Product create(@PathVariable("categoryId") Long categoryId,
                          @RequestBody Product product){

       return categoryRepository.findById(categoryId)
               .map(category -> {
                   product.setCategory(category);
                   return productRepository.save(product);
               }).orElseThrow(() -> new NotFoundException("categoryid not found"));
    }

    @PostMapping(value = "/create/{categoryId}")
    public Product created(@PathVariable("categoryId") Long categoryId,
                           @RequestBody Product product){

        if (product.getId() != null){
            return categoryRepository.findById(categoryId)
                .map(category -> {
                    product.setCategory(category);
                    return productRepository.save(product);
                }).orElseThrow(() -> new NotFoundException("categoryId not found"));
        }else{
            return categoryRepository.findById(categoryId)
                    .map(category -> {
                        product.setCategory(category);
                        return productRepository.save(product);
                    }).orElseThrow(()-> new NotFoundException("categoryId not found"));
        }
    }
}
