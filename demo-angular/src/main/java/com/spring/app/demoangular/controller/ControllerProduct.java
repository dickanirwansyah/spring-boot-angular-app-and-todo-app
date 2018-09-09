package com.spring.app.demoangular.controller;

import com.spring.app.demoangular.entity.Product;
import com.spring.app.demoangular.exception.NotFoundException;
import com.spring.app.demoangular.repository.CategoryRepository;
import com.spring.app.demoangular.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/{productId}")
    public Product getId(@PathVariable("productId") Long productId){
        return productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("productId not found."));
    }

    @PutMapping(value = "/{categoryId}/{productId}")
    public Product update(@PathVariable("categoryId") Long categoryId,
                          @PathVariable("productId") Long productId,
                          @RequestBody Product product){

        return categoryRepository.findById(categoryId)
                .map(category -> {
                    return productRepository.findById(productId)
                            .map(currentProduct -> {
                                currentProduct.setCategory(category);
                                currentProduct.setStock(product.getStock());
                                currentProduct.setPrice(product.getPrice());
                                currentProduct.setName(product.getName());
                                return productRepository.save(currentProduct);
                            }).orElseThrow(() -> new NotFoundException("product Id not found"));
                }).orElseThrow(() -> new NotFoundException("category id not found"));
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
