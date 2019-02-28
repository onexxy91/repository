package com.pshc.hepdre.controller;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pshc.hepdre.aws.service.AwsService;
import com.pshc.hepdre.command.FileCommand;
import com.pshc.hepdre.config.RestURIConstants;
import com.pshc.hepdre.dto.PostRepository;
import com.pshc.hepdre.dto.UserRepository;
import com.pshc.hepdre.model.Member;
import com.pshc.hepdre.model.MemberRole;
import com.pshc.hepdre.model.Post;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@Slf4j
public class MainController {

	private UserRepository userRepository;
	private AwsService awsService;

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

	// 로그인 view
	@RequestMapping("/")
	public String loginView() {
		return "auth/login";
	}
	
	@RequestMapping("/login")
	public String loginForm(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referer);
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/?logout";
	}

	// 회원가입 Proc
	@PostMapping("/members")
	public String insertMember(Member member) {
		MemberRole role = new MemberRole();
		BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
		member.setPassword(pEncoder.encode(member.getPassword()));
		role.setUsername(member.getUsername());
		role.setAuthority("USER");
		member.setRoles(Arrays.asList(role));
		userRepository.save(member);
		
		return "redirect:/";

	}

	/*
	 * ajax 로 post방식 하려했으나 stream 처리불가 로 getMapping
	 */
	@GetMapping(RestURIConstants.GET_FILE_DOWN)
	public void getFileDown(FileCommand fileCommand, HttpServletResponse response) {
		log.info(getClientInfo() + "/filedown?" + fileCommand.getCategory() + " " + fileCommand.getFileName());
		// token 
		OutputStream responseOut = null;
		String category = fileCommand.getCategory();
		String fileName = fileCommand.getFileName();

		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			responseOut = response.getOutputStream();
			awsService.downloadFile(category, fileName, responseOut);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			
			
			
			
		} finally {
			try {
				if (responseOut != null)
					responseOut.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
