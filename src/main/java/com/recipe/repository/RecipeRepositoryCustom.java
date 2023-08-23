package com.recipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeSearchDto;

public interface RecipeRepositoryCustom {
	
	Page<RecipeCategoryDto> getRecipeCategoryReviewBestList(Pageable pageable ,RecipeSearchDto recipeSearchDto);
	
}
