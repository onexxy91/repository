package com.pshc.util.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * Category 
 * -version Column 추후 추가  
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private String autoupdate;
	@Column 
	private String enable; 
	@Column
	private String visible;
	@Column
	private String content; 
	
	@Builder
	public Category(String name , String autoUpdate, String enable
			, String visible, String content) {
		this.name = name; 
		this.autoupdate = autoUpdate;
		this.enable = enable;
		this.visible = visible;
		this.content = content;
	}
}
