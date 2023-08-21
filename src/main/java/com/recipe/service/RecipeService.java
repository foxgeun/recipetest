package com.recipe.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.RecipeMainDto;
import com.recipe.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
	
	private final MemberRepository recipeRepository;
	
	
	
	public Page<RecipeMainDto> getRecipeBestPage(RecipeMainDto recipeMainDto , Pageable pageable) {
		
		
		
		return null;
		
	}
	
}
