package com.pshc.util.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.pshc.util.dto.PostDto;
import com.pshc.util.dto.PostsRepository;
import com.pshc.util.model.Post;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Component
public class PostService {
	private PostsRepository postsRepository;
	
	
	public void create(PostDto postDto) {
		postsRepository.save(postDto.toEntity());
	}
	public List<Post> Postsread() {
		return postsRepository.findAll();
	}
	public Page<Post> PagePostRead(Pageable pageable){
		return postsRepository.findAll(pageable);
	}
	
}
