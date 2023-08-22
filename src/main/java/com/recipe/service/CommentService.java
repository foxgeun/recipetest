package com.recipe.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // 쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
public class CommentService {

	private final CommentRepository commentRepository;

	public Long getCommentCountForMemberAndRecipe(Long memberId, Long recipeId) {
		return commentRepository.getCommentCountForMemberAndRecipe(memberId, recipeId);
	}

}
