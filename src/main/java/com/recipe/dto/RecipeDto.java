package com.recipe.dto;

import java.util.Date;

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
}
