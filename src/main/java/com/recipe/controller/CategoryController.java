package com.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.Recipe;
import com.recipe.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {
	
	@Autowired
	private final RecipeService recipeService;

	
	@GetMapping(value = {"/category" , "/category/{page}"})
	public String categoryOrder(RecipeSearchDto recipeSearchDto ,
			@PathVariable("page") Optional<Integer> page , Model model) {
		
		
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 12);
		Page<Recipe> recipes = recipeService.getRecipePage(pageable);
		model.addAttribute("RecipeList",recipes);
		


		return "category/category";
	}
	
	
	@GetMapping(value = {"/category/{page}/{searchKey}"})
	public String categorySearch(RecipeSearchDto recipeSearchDto ,
			@PathVariable("page") Optional<Integer> page , Model model, @PathVariable("searchKey") String searchKey) {
		
		System.out.println(searchKey+"aksjgbaskgjbaskgjbnaskjgnakj");
		List<Recipe> searchValue = recipeService.getSearchValues(searchKey);
		System.out.println(searchValue);
		
		
		return "category/category";
	}	
}