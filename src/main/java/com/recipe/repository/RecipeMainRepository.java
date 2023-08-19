package com.recipe.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.dto.RecipeMainDto;
import com.recipe.entity.Recipe;


public interface RecipeMainRepository extends JpaRepository<Recipe , Long> , 
										RecipeRepositoryCustom{
	
//	조회수 가장 높은순으로 모든레시피 가져옴 (제한 5개)
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			+ "order by count desc limit 5" , nativeQuery = true)
	List<RecipeMainDto> getRecipeBestList();

//	최근 등록순으로 모든레시피 가져옴 (제한 5개)
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			+ "order by dur_time desc limit 5" , nativeQuery = true)
	List<RecipeMainDto> getRecipeNewList();

//	모든 레시피
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			, nativeQuery = true)
	List<RecipeMainDto> getRecipeTotalList();
	
	
		
	

}
