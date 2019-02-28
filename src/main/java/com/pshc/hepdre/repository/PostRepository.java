package com.pshc.hepdre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pshc.hepdre.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findByDistinction(String distinction);
}
