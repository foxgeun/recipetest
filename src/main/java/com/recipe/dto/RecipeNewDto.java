package com.recipe.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.recipe.entity.Recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeNewDto {
	
	private Long id;
	
	private String title;

	private String subTitle;
	
	private String intro;

	private String durTime;

	private String level;
	
	private String category;

	private String imageUrl;

	private String serving;
	
	private String published;
	
	private String draft;
	
	private int count=0;
	
	
//	재료 정보 저장
	List<RecipeIngreDto> recipeIngreDtoList = new ArrayList<>();
	
//	조리 순서 저장
	List<RecipeOrderDto> recipeOrderDtoList = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
//	dto -> entity 변환
	public Recipe createRecipe() {
		return modelMapper.map(this, Recipe.class);
	}
	
//	entity -> dto 변환
	public static RecipeNewDto of(Recipe recipe) {
		return modelMapper.map(recipe, RecipeNewDto.class);
	}

	
}
