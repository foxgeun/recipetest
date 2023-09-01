package com.recipe.dto;

import com.recipe.constant.ItemCategoryEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
	
	private ItemCategoryEnum itemCategoryEnum;
	
	private String type;
	
	private String searchBy;
	
	private String searchQuery = "";

}

