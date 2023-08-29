package com.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.CommentDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;

	@Transactional(readOnly = true)
	public Page<CommentDto> getAdminCommentPage(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		Page<CommentDto> commentPage = commentRepository.getAdminCommentPage(recipeSearchDto, pageable);
		return commentPage;

	}

}
