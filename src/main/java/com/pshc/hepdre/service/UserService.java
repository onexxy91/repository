package com.pshc.hepdre.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pshc.hepdre.model.Member;
import com.pshc.hepdre.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private UserRepository repository;
	
	public List<Member> findAll(){
		return repository.findAll();
	}
	
	public void save(Member member) {
		repository.save(member);
	}
}
