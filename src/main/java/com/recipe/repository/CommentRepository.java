package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query(value = "SELECT COUNT(c.comment_id) commentCount" + "FROM member m "
			+ "JOIN recipe r ON m.member_id = r.member_id " + "LEFT JOIN comment c ON r.recipe_id = c.recipe_id "
			+ "WHERE m.member_id = :memberId AND r.recipe_id = :recipeId", nativeQuery = true)
	int getCommentCountForMemberAndRecipe(Long memberId, Long recipeId);

	int countByMemberId(Long memberId);
}
