package com.pshc.util.service;

import java.util.List;

import com.pshc.util.dto.PostsRepository;
import com.pshc.util.model.Posts;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class PostsService {
	private PostsRepository postsRepository;
	
	public List<Posts> Postsread() {
		return postsRepository.findAll();
	}
}
