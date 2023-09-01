package com.recipe.dto;

import com.recipe.constant.ItemInqBoardEnum;
import com.recipe.constant.ItemInqEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInqDto {
	
	private Long id;
	
	private String title;
	
	private String content;
	
	private ItemInqBoardEnum itemInqBoardEnum ;
	
	private ItemInqEnum itemInqEnum;
	
}
