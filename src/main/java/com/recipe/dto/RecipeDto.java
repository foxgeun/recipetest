package com.recipe.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.querydsl.core.annotations.QueryProjection;
import com.recipe.entity.Recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDto {
	private Long id;

	private String title;

	private String subTitle;

	private String intro;

	private Date durTime;

	private String description;

	private int level;

	private int count;

	private Long commentCount;

	private String nickname;

	private String imageUrl;
	
	private double rating; 

	private static ModelMapper modelMapper = new ModelMapper();

	// dto -> entity로 바꿈
	public Recipe createRecipe() {
		return modelMapper.map(this, Recipe.class);
	}

	// entity -> dto로 바꿈
	public static RecipeDto of(Recipe recipe) {
		return modelMapper.map(recipe, RecipeDto.class);
	}

	@QueryProjection
	public RecipeDto(Long id, String title, String description, String nickname, Long commentCount, String imageUrl, double rating) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.nickname = nickname;
		this.commentCount = commentCount;
		this.imageUrl = imageUrl;
		this.rating = rating;
	}

}
