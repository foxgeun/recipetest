package com.recipe.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {
	
	private final RecipeService recipeService;
	
	@GetMapping(value = {"/category" , "/category/{page}"})
	public String categoryOrder(RecipeSearchDto recipeSearchDto ,
			@PathVariable("page") Optional<Integer> page , Model model) {
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 12);
		
		Page<RecipeCategoryDto> category = recipeService.getRecipeCategoryReviewBestList(pageable, recipeSearchDto);
		
	
		System.out.println(recipeSearchDto.getType());
		
		
		model.addAttribute("category" , category);
		model.addAttribute("recipeSearchDto" , recipeSearchDto);
		model.addAttribute("maxPage" , 5);
	
		
		return "category";
	}
	
	
	
	
}
