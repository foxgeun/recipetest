package com.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberSearchDto;
import com.recipe.entity.Recipe;
import com.recipe.repository.RecipeListRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // 쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
public class RecipeService {

	private final RecipeListRepository recipeListRepository;

	@Transactional(readOnly = true)
	public Page<Recipe> getAdminRecipePage(MemberSearchDto memberSearchDto, Pageable pageable) {
		Page<Recipe> recipePage = recipeListRepository.getAdminRecipePage(memberSearchDto, pageable);
		return recipePage;

	}

}
