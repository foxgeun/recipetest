package com.recipe.service;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberSearchDto;
import com.recipe.dto.RecipeDto;
import com.recipe.entity.Recipe;
import com.recipe.repository.CommentRepository;
import com.recipe.repository.RecipeListRepository;
import com.recipe.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional // 쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
public class RecipeService {
	
	@Autowired
	private final RecipeListRepository recipeListRepository;
	private final CommentRepository commentRepository;
	private final RecipeRepository recipeRepository;
	
	public Recipe getRecipeDetailByid(Long id) {
		return recipeRepository.getRecipeDetailByid(id);
	}
	
	public Page<Recipe> getRecipePage(Pageable pageable){
		Page<Recipe> recipe = recipeRepository.findAll(pageable);
		
		return recipe;
	}

	@Transactional(readOnly = true)
	public Page<RecipeDto> getAdminRecipePage(MemberSearchDto memberSearchDto, Pageable pageable, Long recipeId) {

		if (recipeId == null) {
			// recipeId가 null일 경우에 대한 처리
			// 예를 들어, 예외 던지기 또는 특정 값을 반환하기 등
			throw new IllegalArgumentException("Recipe ID must not be null.");
		}

		// 레시피 Id로 레시피 조회
		Recipe recipe = recipeListRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

		// 작성자의 댓글 수 조회
		Long memberId = recipe.getMember().getId();
		int commentCount = commentRepository.countByMemberId(memberId);

		System.out.println("commentCount" + commentCount);

		RecipeDto recipeDto = RecipeDto.of(recipe);
		recipeDto.setCommentCount(commentCount);

		System.out.println("recipeDto.getComment" + recipeDto.getCommentCount());
		List<RecipeDto> recipeDtoList = new ArrayList<>();
		recipeDtoList.add(recipeDto);

		// 전체 개수 계산
		Long totalCount = recipeListRepository.count();

		return new PageImpl<>(recipeDtoList, pageable, totalCount);

	}


}
