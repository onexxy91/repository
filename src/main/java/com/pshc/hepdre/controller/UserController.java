package com.pshc.hepdre.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pshc.hepdre.model.Member;
import com.pshc.hepdre.model.MemberRole;
import com.pshc.hepdre.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
	private static final String PREFIX = "user/";

	private UserService service;

	@GetMapping
	public String view(Model model) {
		model.addAttribute("users", service.findAll());
		return PREFIX + "index";
	}

	@GetMapping("/new")
	public String createView() {
		return PREFIX + "new";
	}

	@PostMapping
	public String CreateMember(Member member, HttpServletRequest req) {
		log.info("/createMember");
		service.save(member);
		
		for (String authority : req.getParameterValues("input_roles")) {
			MemberRole role = new MemberRole();
			role.setUsername(member.getUsername());
			role.setAuthority(authority);
		}
		return "redirect:" + PREFIX + "index";
	}

}
