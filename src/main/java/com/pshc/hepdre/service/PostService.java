package com.pshc.hepdre.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.pshc.hepdre.dto.PostDto;
import com.pshc.hepdre.model.Post;
import com.pshc.hepdre.repository.PostRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
@Component
public class PostService {
	private PostRepository postsRepository;
	
	
	public void Postsave(PostDto postDto) {
		postsRepository.save(postDto.toEntity());
	}
	public List<Post> Postsread() {
		return postsRepository.findAll();
	}
	public Page<Post> PagePostRead(Pageable pageable){
		return postsRepository.findAll(pageable);
	}
	
}
