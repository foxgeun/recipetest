package com.recipe.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.recipe.entity.Recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDto {
	private String id;

	private String title;

	private String subTitle;

	private String intro;

	private Date durTime;

	private int level;

	private int count;

	private static ModelMapper modelMapper = new ModelMapper();

	// dto -> entity로 바꿈
	public Recipe createRecipe() {
		return modelMapper.map(this, Recipe.class);
	}

	public static RecipeDto of(Recipe recipe) {
		return modelMapper.map(recipe, RecipeDto.class);
	}
}
