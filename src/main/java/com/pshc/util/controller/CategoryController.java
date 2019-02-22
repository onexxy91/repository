package com.pshc.util.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pshc.util.dto.CategoryDto;
import com.pshc.util.model.Category;
import com.pshc.util.service.CategoryService;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping("/category")
public class CategoryController {

	private static final String PREFIX = "category/";

	private CategoryService categoryService;

	@GetMapping
	public String index(Model model) {
		List<Category> categories = categoryService.categoryRead();

		model.addAttribute("categories", categories);
		return PREFIX + "index";
	}

	@GetMapping("/new")
	public String newCategory() {
		return PREFIX + "new";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id) {
		return PREFIX + "edit";
	}
	
	@GetMapping("/{id}")
	public String getCategory(@PathVariable int id) {
		return PREFIX + "show";
	}
	
	@PostMapping
	public String create(CategoryDto categoryDto, HttpServletRequest request) {
		categoryService.categoryCreate(categoryDto);

		return PREFIX + "index";
	}

	@PutMapping
	public String update(CategoryDto categoryDto) {
		categoryService.categoryUpdate(categoryDto);

		return PREFIX + "index";
	}

	@DeleteMapping
	@ResponseBody
	public String delete(CategoryDto categoryDto) {
		categoryService.categoryDelete(categoryDto);
		return "succes";
	}
}
