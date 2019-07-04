package com.pshc.hepdre.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pshc.hepdre.model.Member;
import com.pshc.hepdre.service.MembersService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping("/members")
public class MemberContorller {
	
	private static final String PREFIX = "members/";
	
	private MembersService memberService;
	
	@GetMapping
	public String mainView(Model model) {
		log.info("/members");

		List<Member> members = memberService.memberRead();
		model.addAttribute("members", members);

		return PREFIX + "index";
	}

}
