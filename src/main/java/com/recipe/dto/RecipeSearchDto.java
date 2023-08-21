package com.recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearchDto {
	
	private String mainCategory;
	
	private String subCategory;
	
	private String type;
	
	private String searchBy;
	
	private String searchQuery = "";
	
	
}
