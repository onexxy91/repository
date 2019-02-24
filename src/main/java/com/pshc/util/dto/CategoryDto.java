package com.pshc.util.dto;

import org.springframework.stereotype.Component;

import com.pshc.util.model.Category;

import lombok.Data;
@Data
@Component
public class CategoryDto {
	private String id;
	private String name;
	private String autoupdate;
	private String enable;
	private String visible;
	private String content;
	
	public Category toEntity() {
		return Category.builder()
				.name(name)
				.autoUpdate(autoupdate)
				.enable(enable)
				.visible(visible)
				.content(content)
				.build();
	}
}
