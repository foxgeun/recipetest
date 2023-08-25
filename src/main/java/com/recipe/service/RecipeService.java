package com.recipe.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	

	
	public Page<Recipe> getRecipePage(Pageable pageable){
		Page<Recipe> recipe = recipeRepository.findAll(pageable);
		
		return recipe;
	}
	
	public List<Recipe> getSearchValues(String searchKey){
		System.out.println(searchKey+"3333333333333333333333");
		List<Recipe> searchValue = recipeRepository.getSearchValues(searchKey);
		return searchValue;
	}
	
	
}
