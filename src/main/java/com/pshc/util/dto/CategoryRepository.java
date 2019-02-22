package com.pshc.util.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pshc.util.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	@Transactional
	@Modifying
	@Query("UPDATE Category c SET c.name = ?1, c.autoupdate = ?2, c.enable = ?3"
			+ ", c.visible = ?4, c.content = ?5 WHERE c.id = ?6") 
	int setCategoryFor(@Param("name")String name, @Param("autoupdate") String autoUpdate
			,@Param("enable") String enable, @Param("visible") String visible
			,@Param("content") String content, @Param("id") Long id);
}
