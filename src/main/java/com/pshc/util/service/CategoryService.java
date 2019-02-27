package com.pshc.util.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pshc.util.dto.CategoryDto;
import com.pshc.util.dto.CategoryRepository;
import com.pshc.util.model.Category;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CategoryService {
	private CategoryRepository categoryRepository;

	public void categoryCreate(CategoryDto categoryDto) {
		categoryRepository.save(categoryDto.toEntity());
	}

	public List<Category> categoryRead() {
		return categoryRepository.findAll();
	}
	
	public Category findCategory(int id) {
		return categoryRepository.findById((long)id).get();
	}

	public void categoryUpdate(CategoryDto categoryDto) {
		categoryRepository.save(categoryDto.toEntity());
	}

	public void categoryDelete(CategoryDto categoryDto) {
		categoryRepository.delete(categoryDto.toEntity());
	}
}
