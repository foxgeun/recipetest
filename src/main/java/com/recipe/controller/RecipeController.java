package com.recipe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.Tuple;
import com.recipe.dto.RecipeIngreDto;
import com.recipe.dto.RecipeNewDto;
import com.recipe.service.RecipeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {

	private final RecipeService recipeService;
	
	@GetMapping(value = "/recipe")
	public String recipe( Model model ) {
		
		RecipeNewDto recipeNewDto = new RecipeNewDto();
		
		model.addAttribute("recipeNewDto" , recipeNewDto);
		
		return "new";
	}
	
	@PostMapping(value = "/recipe/new")
	public String recipeNew(@RequestParam("recipeImgFile") MultipartFile file ,  @Valid RecipeNewDto recipeNewDto ,  
			BindingResult bindingResult , Model model, 
			@RequestParam("RecipeingreName") List<String> RecipeingreMaterialList ,
			@RequestParam("RecipeingreMaterial") List<String> RecipeingreNameList ,
			@RequestParam("recipeOrderContent") List<String> recipeOrderContentList
			) {
			
		System.out.println(file);
		System.out.println(recipeNewDto.getTitle());
		
		try {
			recipeService.saveRecipe(recipeNewDto, RecipeingreMaterialList, 
					RecipeingreNameList , recipeOrderContentList , file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
}
