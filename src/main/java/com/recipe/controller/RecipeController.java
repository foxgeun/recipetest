package com.recipe.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeIngre;
import com.recipe.entity.RecipeOrder;
import com.recipe.repository.RecipeIngreRepository;
import com.recipe.repository.RecipeOrderRepository;
import com.recipe.repository.RecipeRepository;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipe.dto.RecipeNewDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {
	

	@Autowired
	private final RecipeRepository recipeRepository;
	private final RecipeOrderRepository recipeOrderRepository;
	private final RecipeIngreRepository recipeIngreRepository;
	
	@GetMapping(value = "/recipe/{Id}")
	public String recipe(Model model, @PathVariable("Id") Long Id) {
		
		Recipe recipeDetail = recipeRepository.getRecipeDetailByid(Id);
		
		recipeRepository.setaddview(Id);
		List<RecipeOrder> recipeOrderList = recipeOrderRepository.getRecipeOrderByid(Id);
		List<RecipeIngre> recipeIngreList = recipeIngreRepository.getRecipeIngreByid(Id);
		
		
		model.addAttribute("recipeIngreList" ,recipeIngreList);
		model.addAttribute("recipeOrder", recipeOrderList);
		model.addAttribute("recipeDetail",recipeDetail);
		
		return "recipes/recipe";
	}
	@GetMapping(value = "/recipe")
	public String recipe( Model model ) {
		
		RecipeNewDto recipeNewDto = new RecipeNewDto();

		
		model.addAttribute("recipeNewDto" , recipeNewDto);
		
		return "new";
	}

	
	@PostMapping(value = "/recipe/new")
	public String recipeNew(@Valid RecipeNewDto recipeNewDto ,  
			BindingResult bindingResult , Model model, 
			@RequestParam("RecipeIngreList") List<Object> RecipeIngreList ,
			@RequestParam("recipeOrderDtoList") List<Object> recipeOrderDtoList ) {
			
		for(Object d : RecipeIngreList) {
			System.out.println("object1 = " + d);
		}
		
		for(Object d : recipeOrderDtoList) {
			System.out.println("object2 = " + d);
		}
		
		
		
		
		
		return "redirect:/";
	}
	
}
