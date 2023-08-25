package com.recipe.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.recipe.entity.Recipe;

import jakarta.transaction.Transactional;



public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	

	
	boolean existsByTitle(String title);
	
	

	@Query(value = "select * from recipe where recipe_id = ?1", nativeQuery = true)
	Recipe getRecipeDetailByid(Long id);
	
	
	@Transactional
	@Query(value = "select * from recipe", nativeQuery = true)
	List<Recipe> getAllRecipeList();	
	
	@Transactional
	@Modifying
	@Query(value = "update recipe set count = count + 1 where recipe_id = ?1", nativeQuery = true)
	int setaddview(Long id);	
	
	
	@Query(value = "SELECT * FROM recipe WHERE member_id = ?1", nativeQuery = true)
	List<Recipe> findRecipe(Long id);

	
}
