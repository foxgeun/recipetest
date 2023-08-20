package com.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.entity.Recipe;
import com.recipe.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {
	
	@Autowired
	private final RecipeRepository recipeRepository;
	
	@GetMapping(value = "/recipe/{Id}")
	public String recipe(Model model, @PathVariable("Id") Long Id) {
		
		Recipe recipeDetail = recipeRepository.getRecipeDetailByid(Id);
		
		model.addAttribute("recipeDetail",recipeDetail);
		
		return "recipes/recipe";
	}
	
	
	
}
