package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.recipe.entity.Recipe;



public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	

	
	boolean existsByTitle(String title);
	
	

	@Query(value = "select * from recipe where recipe_id = ?1", nativeQuery = true)
	Recipe getRecipeDetailByid(Long id);
	
	
	
}
