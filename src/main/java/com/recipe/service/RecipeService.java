package com.recipe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.RecipeMainDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
	
	
	public List<RecipeMainDto> RecipeGetBestPage(RecipeMainDto recipeMainDto) {
		
		
		return null;
		
	}
	
}
