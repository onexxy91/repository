package com.pshc.util.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private String used; 
	@Column
	private String visible;
	@Column
	private String content;
	
	@Builder
	public Category(String name , String used, String visible, String content) {
		this.name = name; 
		this.used = used;
		this.visible = visible;
		this.content = content;
	}
}
