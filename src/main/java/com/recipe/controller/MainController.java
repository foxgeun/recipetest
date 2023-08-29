package com.recipe.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipe.dto.MemberMainDto;
import com.recipe.dto.RecipeMainDto;
import com.recipe.entity.Member;
import com.recipe.oauth.PrincipalDetails;
import com.recipe.service.MemberService;
import com.recipe.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final RecipeService recipeService;
	
	private final MemberService memberService;
	
	@GetMapping(value = "/")
	public String main(Model model, Authentication authentication) {
		
//		PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        Member member = principal.getMember();
		
		
	    List<RecipeMainDto> mainBestList = recipeService.getRecipeBestList(); // best 레시피
	    model.addAttribute("mainBestList" , mainBestList);
	    
	    List<RecipeMainDto> mainNewList = recipeService.getRecipeNewList(); // new 레시피
	    model.addAttribute("mainNewList" , mainNewList);
	    
	    List<RecipeMainDto> mainTotalList = recipeService.getRecipeTotalList(); // 모든 레시피
	    model.addAttribute("mainTotalList" , mainTotalList);
	    
	    List<MemberMainDto> mainMemberlList = memberService.getMemberBestList(); // best 회원
	    model.addAttribute("mainMemberlList" , mainMemberlList);
	    
	
		return "main";
	}
	
}
