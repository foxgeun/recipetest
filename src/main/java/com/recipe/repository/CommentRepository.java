package com.recipe.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.dto.CommentDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<CommentDto> getAdminCommentPage(RecipeSearchDto recipeSearchDto, Pageable pageable);
}
