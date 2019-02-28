package com.pshc.util.dto;

import org.springframework.stereotype.Component;

import com.pshc.util.model.Category;

import lombok.Data;
@Data
@Component
public class CategoryDto {
	private String id;
	private String name;
	private String used;
	private String visible;
	private String content;
	
	public Category toEntity() {
		return Category.builder()
				.name(name)
				.used(used)
				.visible(visible)
				.content(content)
				.build();
	}
	public String toString(CategoryDto categoryDto) {
		return categoryDto.id+"      "+
	            categoryDto.used+"       "+
	            categoryDto.visible+"          "+
	            categoryDto.content;
				
	}
}
