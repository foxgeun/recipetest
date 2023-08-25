package com.recipe.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

public interface RecipeMainDto {
	
	String getId();
	
	String getTitle();
	
	String getSubTitle();
	
	String getIntro();
	
	String getDurTime();
	
    int getLevel();
	
    int getCount();
	
}
