package com.pshc.hepdre.dto;

import org.springframework.stereotype.Component;

import com.pshc.hepdre.model.Category;
import com.pshc.hepdre.model.Post;

import lombok.Data;
@Component
@Data
public class PostDto {
	private String id;
	private String name;
	private String activated;
	private String distinction;
	private String fileSize;
	private String filePath;
	private String ver;
	private String content;
	private Category category;
	
	public Post toEntity() {
		return Post.builder()
				.name(name)
				.activated(activated)
				.distinction(distinction)
				.fileSize(fileSize)
				.filePath(filePath)
				.ver(ver)
				.content(content)
				.category(category)
				.build();
	}
	public String toString() {
		return name.toLowerCase()+"          "+
				activated+"          "+
				distinction+"          "+
				fileSize+"              "+
				name+"        "+id;
	}
}
