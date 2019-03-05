package com.pshc.hepdre.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.pshc.hepdre.model.Post;
import com.pshc.hepdre.service.CategoryService;
import com.pshc.hepdre.service.FileUploadService;
import com.pshc.hepdre.service.PostService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

	private static final String PREFIX = "post/";
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

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Post post = postService.findById(id);
		model.addAttribute("post", post);
		return PREFIX + "edit";
	}

	@GetMapping("/{id}/download")
	public void download(HttpServletResponse response) {
		
	}

	@GetMapping("/new")
	public String newPost(Model model, @RequestParam int categoryId) {
		PostDto post = new PostDto();
		post.setCategory(categoryService.findCategory(categoryId));
		model.addAttribute("post", post);
		
		return PREFIX + "new";
	}

	@PostMapping
	public String create(PostDto postDto,
						@RequestPart MultipartFile file, 
						HttpServletRequest request,
						Model model) {
		// file upload check 필요함. !!!!
		if (!file.isEmpty()) {
			fileUpload.upload(request, file);
			postService.create(postDto);
		}
		
		// error checking 필요
		return "redirect:/category/" + postDto.getCategory().getId();
	}

	@PutMapping
	public String update(PostDto postDto) {
		Post post = postService.update(postDto);
		return "redirect:/category/" + post.getCategory().getId();
	}

	@DeleteMapping
	public String delete() {
		return "redirect:/category/" + "1";
	}
}
