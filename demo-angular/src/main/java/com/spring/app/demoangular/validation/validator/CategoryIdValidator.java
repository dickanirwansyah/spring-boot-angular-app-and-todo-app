package com.spring.app.demoangular.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.app.demoangular.entity.Category;
import com.spring.app.demoangular.repository.CategoryRepository;
import com.spring.app.demoangular.validation.CategoryIdValidation;

@Component
public class CategoryIdValidator implements ConstraintValidator<CategoryIdValidation, Long>{

	@Autowired private CategoryRepository categoryRepository;
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if(value == null || value == 0) {
			return false;
		}
		
		Optional<Category> categoryOptional = categoryRepository.findById(value);
		if (!categoryOptional.isPresent()) {
			return false;
		}
		
		return true;
	}

	
}
