package com.pshc.util.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.pshc.util.dto.CategoryRepository;
import com.pshc.util.dto.PostsDto;
import com.pshc.util.dto.PostsRepository;
import com.pshc.util.model.Category;
import com.pshc.util.model.Posts;
import com.pshc.util.service.FileUploadService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {
	
	private static final String PREFIX = "post/";

	private PostsRepository postsRepository;
	private CategoryRepository categoryRepository;
	private PostsDto postsDto;
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
	
	@GetMapping("/{id}")
	public String show(@PathVariable int id, Model model) {
		//find post 
		model.addAttribute("post",new PostsDto());
		return PREFIX + "show";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		//find post 
		model.addAttribute("post",new PostsDto());
		return PREFIX + "edit";
	}
	
	@GetMapping("/new")
	public String newPost(Model model) {
		model.addAttribute("post",new PostsDto());
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
	public String updatePosts(PostsDto posts, HttpServletRequest request) {
		log.info("/updatepost " + posts.getCategory() + " " + posts.getDistinction() + " " + posts.getId());
		postsRepository.setDistinctionFor(posts.getDistinction(), Long.parseLong(posts.getId()));
		return "redirect:/posts";

	}
}
