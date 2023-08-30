package com.recipe.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemReviewDto {
	
	private Long id;
	
	private double reting;
	
	private String content;
	
	private LocalDateTime regTime;
	
	private String nickname;
	
	private String imgUrl;
	
	@QueryProjection
	public ItemReviewDto(Long id ,double reting , String content ,LocalDateTime regTime , String nickname , String imgUrl) {
		this.id = id;
		this.reting = reting;
		this.content = content;
		this.regTime = regTime;
		this.nickname = nickname;
		this.imgUrl = imgUrl;
		
	}
	
	
}
