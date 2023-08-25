package com.recipe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipe.dto.RecipeNewDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {

	
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
