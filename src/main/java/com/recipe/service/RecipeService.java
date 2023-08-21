package com.recipe.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.entity.Recipe;
import com.recipe.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {

	@Autowired
	private final RecipeRepository recipeRepository;
	
	public Recipe getRecipeDetailByid(Long id) {
		return recipeRepository.getRecipeDetailByid(id);
	}
	
	
}