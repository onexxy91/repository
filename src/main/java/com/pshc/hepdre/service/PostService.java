package com.pshc.hepdre.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pshc.hepdre.dto.PostDto;
import com.pshc.hepdre.model.Post;
import com.pshc.hepdre.repository.PostRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Component
public class PostService {
	
	private PostRepository postRepository;
	
	public List<Post> read() {
		return postRepository.findAll();
	}
	public void create(PostDto postDto) {
		postRepository.save(postDto.toEntity());
	}
	
	public Post update(PostDto postDto) {
		return postRepository.save(postDto.toEntity());
	}
	
	public Post findById(int id) {
		return postRepository.findById(id).get();
	}
	public Page<Post> PagePostRead(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
	public List<Post> findByDistinction(String distinction){
		return postRepository.findByDistinction(distinction);
	}
	
}
