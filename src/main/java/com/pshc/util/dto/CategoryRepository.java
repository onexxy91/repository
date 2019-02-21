package com.pshc.util.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pshc.util.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	/*
	 * @Query("select count(*) from Category") int findBy(@Param("distinction")
	 * String distinction, @Param("id") Long id);
	 */
}
