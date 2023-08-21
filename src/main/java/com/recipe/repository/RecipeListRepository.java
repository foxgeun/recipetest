package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.Recipe;

public interface RecipeListRepository extends JpaRepository<Recipe, Long>, RecipeListRepositoryCustom {

	boolean existsByTitle(String title);

	// select * from item where item_nm = ?
	List<Recipe> findByRecipeId(Long recipeId);

	// select * from Recipe where member_id = ?
	List<Recipe> findByMemberId(Long memberId);
}
