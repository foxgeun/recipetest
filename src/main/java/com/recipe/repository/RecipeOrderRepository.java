package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.RecipeOrder;

public interface RecipeOrderRepository extends JpaRepository<RecipeOrder, Long>{

}
