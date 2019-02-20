package com.pshc.util.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pshc.util.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
