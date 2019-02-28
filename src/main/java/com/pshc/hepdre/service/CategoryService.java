package com.pshc.hepdre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pshc.hepdre.dto.CategoryDto;
import com.pshc.hepdre.model.Category;
import com.pshc.hepdre.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CategoryService {
	private CategoryRepository categoryRepository;

	public void categoryCreate(CategoryDto categoryDto) {
		categoryRepository.save(categoryDto.savetoEntity());
	}

	public List<Category> categoryRead() {
		return categoryRepository.findAll();
	}
	
	public Category findCategory(int id) {
		return categoryRepository.findById(id).get();
	}

//	public void categoryUpdate(CategoryDto categoryDto) {
//		
//		Long id = Long.parseLong(categoryDto.getId());
//		String name = categoryDto.getName();
//		String autoUpdate = categoryDto.getAutoupdate();
//		String enable = categoryDto.getEnable();
//		String visible = categoryDto.getVisible();
//		String content = categoryDto.getContent();
//
//		categoryRepository.setCategoryFor(name, autoUpdate, enable, visible, content, id);
//	}
	public void categoryUpdate(CategoryDto categoryDto) {
		categoryRepository.save(categoryDto.toEntity());
	}

	public void categoryDelete(CategoryDto categoryDto) {
		categoryRepository.delete(categoryDto.toEntity());
	}
}
