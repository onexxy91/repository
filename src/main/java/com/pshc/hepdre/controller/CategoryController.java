package com.pshc.hepdre.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pshc.hepdre.dto.CategoryDto;
import com.pshc.hepdre.model.Category;
import com.pshc.hepdre.model.Post;
import com.pshc.hepdre.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping("/category")
public class CategoryController {

	private static final String PREFIX = "category/";

	private CategoryService categoryService;

	@GetMapping
	public String mainView(Model model) {
		log.info("/category");

		List<Category> categories = categoryService.categoryRead();
		model.addAttribute("categories", categories);

		return PREFIX + "index";
	}

	@GetMapping("/{id}/download")
	public String downloadPost(@PathVariable int id) {

		return "";
	}

	@GetMapping("/new")
	public String CreateView(Model model) {
		log.info(PREFIX + "/new");

		model.addAttribute("category", new CategoryDto());

		return PREFIX + "new";
	}

	@GetMapping("/edit/{id}")
	public String EditView(@PathVariable int id, Model model) {
		log.info(PREFIX + "/edit{" + id + "}");

		model.addAttribute("category", categoryService.findCategory(id));

		return PREFIX + "edit";
	}

	@GetMapping("/{id}")
	public String DetailView(@PathVariable int id, Model model) {
		Category category = categoryService.findCategory(id);
		List<Post> postList = category.getPosts();
		for (Post post : postList) {
			post.getName();
		}
		model.addAttribute("category", categoryService.findCategory(id));
		return PREFIX + "detail";
	}

	@PostMapping
	public String create(CategoryDto categoryDto, HttpServletRequest request) {
		log.info(PREFIX + "create/ " + categoryDto.toString());

		categoryService.categoryCreate(categoryDto);

		return "redirect:" + PREFIX;
	}

	@PutMapping
	public String update(CategoryDto categoryDto) {
		log.info(PREFIX + "update/ " + categoryDto.toString());

		categoryService.categoryUpdate(categoryDto);

		return "redirect:" + PREFIX;
	}

	@DeleteMapping
	@ResponseBody
	public String delete(@RequestBody CategoryDto categoryDto) {
		log.info(PREFIX + "delete/ " + categoryDto.toString());

		categoryService.categoryDelete(categoryDto);

		return "success";
	}

}
