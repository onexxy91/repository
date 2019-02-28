package com.pshc.hepdre.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.pshc.hepdre.dto.PostDto;
import com.pshc.hepdre.dto.service.CategoryService;
import com.pshc.hepdre.dto.service.PostService;
import com.pshc.hepdre.model.Category;
import com.pshc.hepdre.model.Post;
import com.pshc.hepdre.service.FileUploadService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {
	
	private static final String PREFIX = "post/";

	//private PostsRepository postsRepository;
	//private CategoryRepository categoryRepository;
	//private PostDto postsDto;
	private PostService postService;
	private CategoryService categoryService;
	private FileUploadService fileUpload;

	protected String getRemoteIp() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		log.debug(req.getHeader("user-agent"));

		return ip;
	}

	protected String getClientInfo() {
		return "C:" + getRemoteIp() + ", Rq:";
	}

	@GetMapping("/posts")
	public String postsView(Model model,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 10) Pageable pageable) {
		//Page<Post> postsList = postsRepository.findAll(pageable);
		Page<Post> postList = postService.PagePostRead(pageable);
		//List<Category> categoryList = categoryRepository.findAll();
		List<Category> categoryList = categoryService.categoryRead();
		
		
		model.addAttribute("postslist", postList);
		model.addAttribute("categorys", categoryList);
		return "heag";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		//find post 
		model.addAttribute("post",new PostDto());
		return PREFIX + "edit";
	}
	
	@GetMapping("/new")
	public String newPost(Model model, @RequestParam int categoryId) {
		PostDto post = new PostDto();
		post.setCategoryId(categoryId);
		model.addAttribute("post",post);
		return PREFIX + "new";
	}
	
	@PostMapping
	public String create() {
		return "redirect:/category/"+"1";
	}
	
	@PutMapping
	public String update() {
		return "redirect:/category/"+"1";
	}
	
	@DeleteMapping
	public String delete() {
		return "redirect:/category/"+"1";
	}

	@RequestMapping("/uploadfile")
	public String uploadFile(@RequestPart MultipartFile files, HttpServletRequest request) {
		log.info(getClientInfo() + " /uploadfile " + files.getOriginalFilename());

		if (!files.isEmpty()) {
			fileUpload.doWork(request, files);
			log.info("111111111");
		}
		return "redirect:/posts";
	}

	@PostMapping("/updatepost")
	public String updatePosts(PostDto postDto, HttpServletRequest request) {
		log.info("/updatepost " + postDto.getName() + " " + postDto.getDistinction() + " " + postDto.getId());
		//postsRepository.setDistinctionFor(postDto.getDistinction(), Long.parseLong(postDto.getId()));
		postService.Postsave(postDto);
		
		return "redirect:/posts";

	}
}
