package com.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberSearchDto;
import com.recipe.dto.RecipeDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.dto.SearchWrapper;
import com.recipe.repository.RecipeListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
@Transactional
public class RecipeService {

	private final RecipeListRepository recipeListRepository;

	@Transactional(readOnly = true)
	public Page<RecipeDto> getAdminRecipePage(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		Page<RecipeDto> getAdminRecipePage = recipeListRepository.getAdminRecipePage(recipeSearchDto, pageable);
		return getAdminRecipePage;

	}

}
