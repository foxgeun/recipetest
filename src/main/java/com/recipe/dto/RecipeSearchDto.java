package com.recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearchDto {
	private String searchBy;
	private String searchQuery = "";
	private String sort;
	
}
