package com.recipe.dto;

import java.time.LocalDateTime;

public interface RecipeCategoryDto {
	
	 Long getMemberId();
	
	 Long getRecipeId();
	
	 int getNum();
	
	 int getBookmarkCount();
	
	 int getCount();
	
	 String getDurTime();
	
	 String getLevel();
	
	 String getDescription();
	
	 String getTitle();
	
	 String getSubTitle();

	 String getIntro();
	
	 String getImageUrl();
	
	 LocalDateTime getRegTime();
	
	

	
	
}
