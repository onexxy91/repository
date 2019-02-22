package com.pshc.util.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pshc.util.dto.CategoryDto;
import com.pshc.util.model.Category;
import com.pshc.util.service.CategoryService;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@Slf4j
public class CategoryController {

	private static final String PREFIX = "category/";

	// private CategoryRepository categoryRepository;

	private CategoryService categoryService;

	@GetMapping
	public String index(Model model) {
		List<Category> categories = categoryService.categoryRead();

		model.addAttribute("categories", categories);
		return PREFIX + "index";
	}

	@PostMapping
	public String create(CategoryDto categoryDto, HttpServletRequest request) {
		categoryService.categoryCreate(categoryDto);

		return PREFIX + "create";
	}

	@PutMapping
	public String edit(CategoryDto categoryDto) {
		categoryService.categoryUpdate(categoryDto);

		return PREFIX + "edit";
	}

	@DeleteMapping
	@ResponseBody
	public String delete(CategoryDto categoryDto) {
		categoryService.categoryDelete(categoryDto);

		return "succes";
	}
}
