package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.RecipeIngre;

public interface RecipeIngreRepository extends JpaRepository<RecipeIngre, Long>{

}
