package com.recipe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recipe.dto.MemberBestDto;
import com.recipe.dto.MemberMainDto;
import com.recipe.dto.RecipeMainDto;
import com.recipe.service.MemberService;
import com.recipe.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final RecipeService recipeService;
	
	private final MemberService memberService;
	
	@GetMapping(value = "/")
	public String main(Model model) {
		
	    List<RecipeMainDto> headerBestList = recipeService.getRecipeHeaderBestList(); // 헤더 레시피
	    model.addAttribute("headerBestList" , headerBestList);
	    
	    List<RecipeMainDto> mainBestList = recipeService.getRecipeBestList(); // 베스트 레시피
	    model.addAttribute("mainBestList" , mainBestList);
	    
	    List<RecipeMainDto> mainNewList = recipeService.getRecipeNewList(); // new 레시피
	    model.addAttribute("mainNewList" , mainNewList);
	    
	    List<RecipeMainDto> mainTotalList = recipeService.getRecipeTotalList(); // 모든 레시피
	    model.addAttribute("mainTotalList" , mainTotalList);
	    
	    List<MemberBestDto> rankMemberList = memberService.getRankMemberList();
	    model.addAttribute("rankMemberList",rankMemberList);
	    
	
		return "main";
	}
	
//	팔로우 하기
	@PostMapping(value="/followReq")
	public @ResponseBody ResponseEntity inqReq(@RequestBody Map<String, Object> requestBody) {
		
		Long id = Long.parseLong(requestBody.get("id").toString());
		
		System.out.println("id :::::::: " + id);
		
		return new ResponseEntity<>("팔로우 되셨습니다." , HttpStatus.OK);
	}
	
}
