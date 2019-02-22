package com.pshc.util.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pshc.util.dto.CategoryRepository;
import com.pshc.util.model.Category;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@Slf4j
public class CategoryController {
	
	private static final String PREFIX="category/";
	
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public String index(Model model) {
		List<Category> categories = categoryRepository.findAll();
		
		model.addAttribute("categories",categories);
		return PREFIX+"index";
	}
	
	@PostMapping
	public String create(Category category) {
		
		return PREFIX+"create";
	}
	
	@PutMapping
	public String edit(Category category) {
		
		return PREFIX+"edit";
	}
	
	@DeleteMapping
	@ResponseBody
	public String delete(Category category) {
		
		return "succes";
	}
}
