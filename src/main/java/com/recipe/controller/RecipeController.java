package com.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {

	
	@GetMapping(value = "/recipe")
	public String recipe() {
		
		
		return "recipes/recipe";
	}
	
	
	
}
