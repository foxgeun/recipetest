package com.recipe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.dto.RecipeMainDto;
import com.recipe.repository.RecipeMainRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final RecipeMainRepository recipeMainRepository;
	
	@GetMapping(value = "/")
	public String main(Model model) {
		
	    List<RecipeMainDto> mainBestList = recipeMainRepository.getRecipeBestList(); // best 레시피
	    model.addAttribute("mainBestList" , mainBestList);
	    
	    List<RecipeMainDto> mainNewList = recipeMainRepository.getRecipeNewList(); // new 레시피
	    model.addAttribute("mainNewList" , mainNewList);
	    
	    List<RecipeMainDto> mainTotalList = recipeMainRepository.getRecipeTotalList(); // 모든 레시피
	    model.addAttribute("mainTotalList" , mainTotalList);
	    
	    
		return "main";
	}
	
}
