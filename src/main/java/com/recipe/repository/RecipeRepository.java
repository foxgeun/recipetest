package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipe.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

	
	@Query(value = "SELECT * FROM recipe WHERE member_id = ?1", nativeQuery = true)
	List<Recipe> findRecipe(Long id);
	
}
