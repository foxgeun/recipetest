package com.recipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recipe.dto.MemberSearchDto;
import com.recipe.dto.RecipeDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.dto.SearchWrapper;
import com.recipe.entity.Recipe;

public interface RecipeListRepositoryCustom {

	Page<RecipeDto> getAdminRecipePage(RecipeSearchDto recipeSearchDto, Pageable pageable);

}
