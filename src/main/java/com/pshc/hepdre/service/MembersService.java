package com.pshc.hepdre.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pshc.hepdre.model.Member;
import com.pshc.hepdre.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MembersService {
	private UserRepository userRepository;

	public List<Member> memberRead() {
		List<Member> members = userRepository.findAll();
		return members;
	}

}
