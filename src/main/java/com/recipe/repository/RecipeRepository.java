package com.recipe.repository;


import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeDto;
import com.recipe.dto.RecipeSearchDto;
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
	
	
	@Query(value = "select * from recipe where level = ?1", nativeQuery = true)
	List<Recipe> getSearchValues(String searchKey);

	
	
////	조회수 가장 높은순으로 모든레시피 가져옴 (제한 5개)
////	메인
//	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
//			+ "recipe.intro intro , recipe.dur_time durTime , "
//			+ "recipe.level level , recipe.count count "
//			+ "from recipe "
//			+ "order by count desc limit 5" 
//			, nativeQuery = true)
//	List<RecipeDto> getRecipeBestList();
//
////	최근 등록순으로 모든레시피 가져옴 (제한 5개)
////	메인
//	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
//			+ "recipe.intro intro , recipe.dur_time durTime , "
//			+ "recipe.level level , recipe.count count "
//			+ "from recipe "
//			+ "order by dur_time desc limit 5" 
//			, nativeQuery = true)
//	List<RecipeDto> getRecipeNewList();
//
////	모든 레시피
////	메인
//	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
//			+ "recipe.intro intro , recipe.dur_time durTime , "
//			+ "recipe.level level , recipe.count count "
//			+ "from recipe "
//			, nativeQuery = true)
//	List<RecipeDto> getRecipeTotalList();
//	
////	모든 레시피와 북마크수 (최근 등록순)
////	카테고리 페이지
//	@Query( value = "select ROW_NUMBER() OVER (ORDER BY r.reg_time desc) num ,"
//			+ "COUNT(bm.recipe_id) bookmarkCount , r.count count , r.dur_time durTime , r.level level , r.description description , "
//			+ "r.title title , r.sub_title subTitle , r.intro intro , r.image_url imageUrl , "
//			+ "r.reg_time regTime , r.member_id memberId , r.recipe_id recipeId "
//			+ "from recipe r "
//			+ "LEFT JOIN book_mark bm ON r.recipe_id = bm.recipe_id "
//			+ "GROUP BY r.recipe_id "
//			+ "ORDER BY r.reg_time desc" ,
//			countQuery = "SELECT COUNT(*) FROM recipe " ,
//		    nativeQuery = true)
//	Page<RecipeCategoryDto> getRecipeCategoryOrderList(Pageable pageable , RecipeSearchDto recipeSearchDto);
//	
////	모든 레시피와 북마크수 (북마크 높은순)
////	카테고리 페이지
//	@Query( value = "select ROW_NUMBER() OVER (ORDER BY COUNT(bm.recipe_id) desc) num ,"
//			+ "COUNT(bm.recipe_id) bookmarkCount , r.count count , r.dur_time durTime , r.level level , r.description description , "
//			+ "r.title title , r.sub_title subTitle , r.intro intro , r.image_url imageUrl , "
//			+ "r.reg_time regTime , r.member_id memberId , r.recipe_id recipeId "
//			+ "from recipe r "
//			+ "LEFT JOIN book_mark bm ON r.recipe_id = bm.recipe_id "
//			+ "GROUP BY r.recipe_id "
//			+ "ORDER BY COUNT(bm.recipe_id) desc" ,
//			countQuery = "SELECT COUNT(*) FROM recipe " ,
//		    nativeQuery = true)
//	Page<RecipeCategoryDto> getRecipeCategoryBestList(Pageable pageable , RecipeSearchDto recipeSearchDto);

	
}
