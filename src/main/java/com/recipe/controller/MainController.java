package com.recipe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.dto.MemberMainDto;
import com.recipe.dto.RecipeMainDto;
import com.recipe.repository.MemberRepository;
import com.recipe.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final RecipeRepository recipeRepository;
	
	private final MemberRepository memberRepository;
	
	@GetMapping(value = "/")
	public String main(Model model) {
		
	    List<RecipeMainDto> mainBestList = recipeRepository.getRecipeBestList(); // best 레시피
	    model.addAttribute("mainBestList" , mainBestList);
	    
	    List<RecipeMainDto> mainNewList = recipeRepository.getRecipeNewList(); // new 레시피
	    model.addAttribute("mainNewList" , mainNewList);
	    
	    List<RecipeMainDto> mainTotalList = recipeRepository.getRecipeTotalList(); // 모든 레시피
	    model.addAttribute("mainTotalList" , mainTotalList);
	    
	    List<MemberMainDto> mainMemberlList = memberRepository.getMemberBestList(); // best 회원
	    model.addAttribute("mainMemberlList" , mainMemberlList);
	    
	
		return "main";
	}
	
}
