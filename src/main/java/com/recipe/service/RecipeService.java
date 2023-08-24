package com.recipe.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeMainDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
	
	private final RecipeRepository recipeRepository;
	
	
	
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeNewList() {
		List<RecipeMainDto> getRecipeNewList = recipeRepository.getRecipeNewList();
		return getRecipeNewList;
	}
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeBestList() {
		List<RecipeMainDto> getRecipeBestList = recipeRepository.getRecipeBestList();
		return getRecipeBestList; 
	}
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeTotalList() {
		List<RecipeMainDto> getRecipeTotalList = recipeRepository.getRecipeTotalList();
		return getRecipeTotalList; 
	}
	
	@Transactional(readOnly = true)
	public Page<RecipeCategoryDto> getRecipeCategoryReviewBestList(Pageable pageable , RecipeSearchDto recipeSearchDto) {
		Page<RecipeCategoryDto> getRecipeCategoryReviewBestList = recipeRepository.getRecipeCategoryReviewBestList(pageable, recipeSearchDto);
	    return getRecipeCategoryReviewBestList;
	}
	
}
