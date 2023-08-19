package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.Recipe;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
	List<Recipe> findByid(Long RecipeId);
	
	
}
