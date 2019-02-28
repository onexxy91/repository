package com.pshc.hepdre.dto;

import org.springframework.stereotype.Component;

import com.pshc.hepdre.model.Category;

import lombok.Data;
@Data
@Component
public class CategoryDto {
	private String id;
	private String name;
	private String used;
	private String visible;
	private String content;
	
	public Category savetoEntity() {
		return Category.builder()
				.name(name)
				.used(used)
				.visible(visible)
				.content(content)
				.build();
	}
	public Category toEntity() {
		return Category.builder()
				.id(Integer.parseInt(id))
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
