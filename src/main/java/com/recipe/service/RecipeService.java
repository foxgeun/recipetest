package com.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.RecipeDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.Recipe;
import com.recipe.repository.CommentRepository;
import com.recipe.repository.RecipeListRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
@Transactional
public class RecipeService {

	private final RecipeListRepository recipeListRepository;
	private final CommentRepository commentRepository;

	@Transactional(readOnly = true)
	public Page<RecipeDto> getAdminRecipePage(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		Page<RecipeDto> getAdminRecipePage = recipeListRepository.getAdminRecipePage(recipeSearchDto, pageable);
		return getAdminRecipePage;

	}

	// 레시피 삭제
	public void deleteRecipe(Long recipeId) {

		commentRepository.deleteByRecipeId(recipeId);
		// ★delete하기 전에 select를 한번 해준다
		// ->영속성 컨텍스트에 엔티티를 저장한 후 변경 감지를 하도록 하기 위해
		Recipe recipe = recipeListRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

		// delete
		recipeListRepository.delete(recipe);
	}

}
