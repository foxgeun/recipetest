package com.recipe.dto;



import com.recipe.constant.CategoryEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearchDto {

	

	private String subCategory;

	private CategoryEnum mainCategory;

	private String type;
	

	private String searchBy;
	
	
	private String searchQuery = "";
	
	
	private String sort;
	

	
}
