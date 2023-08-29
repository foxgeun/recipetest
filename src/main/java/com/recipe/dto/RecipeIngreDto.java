package com.recipe.dto;

import org.modelmapper.ModelMapper;

import com.recipe.entity.RecipeIngre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeIngreDto {
	
	private Long id;
	
	private String ingreMaterial;
	
	private String ingreName;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static RecipeIngreDto of(RecipeIngre recipeIngre) {
		return modelMapper.map(recipeIngre, RecipeIngreDto.class);
		
	}
}
