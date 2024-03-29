package com.pshc.hepdre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pshc.hepdre.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}
