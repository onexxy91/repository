package com.pshc.hepdre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private String activated;
	@Column
	private String distinction;
	@Column
	private String fileSize;
	@Column 
	private String filePath;
	@Column
	private String ver;
	@Column
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@Builder
	public Post(String activated, String distinction, String category,
					String ver, String fileSize, String filePath, String name,
						String content) {
		this.name = name;
		this.activated = activated;
		this.distinction = distinction;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.ver = ver;
		this.content = content;
	}
}
