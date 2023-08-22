package com.recipe.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.dto.MemberMainDto;
import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeMainDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.Recipe;


public interface RecipeRepository extends JpaRepository<Recipe , Long>  
										{
	
//	조회수 가장 높은순으로 모든레시피 가져옴 (제한 5개)
//	메인
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			+ "order by count desc limit 5" 
			, nativeQuery = true)
	List<RecipeMainDto> getRecipeBestList();

//	최근 등록순으로 모든레시피 가져옴 (제한 5개)
//	메인
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			+ "order by dur_time desc limit 5" 
			, nativeQuery = true)
	List<RecipeMainDto> getRecipeNewList();

//	모든 레시피
//	메인
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			, nativeQuery = true)
	List<RecipeMainDto> getRecipeTotalList();
	
//	모든 레시피와 북마크수 (최근 등록순)
//	카테고리 페이지
	@Query( value = "select ROW_NUMBER() OVER (ORDER BY r.reg_time desc) num ,"
			+ "COUNT(bm.recipe_id) bookmarkCount , r.count count , r.dur_time durTime , r.level level , r.description description , "
			+ "r.title title , r.sub_title subTitle , r.intro intro , r.image_url imageUrl , "
			+ "r.reg_time regTime , r.member_id memberId , r.recipe_id recipeId "
			+ "from recipe r "
			+ "LEFT JOIN book_mark bm ON r.recipe_id = bm.recipe_id "
			+ "GROUP BY r.recipe_id "
			+ "ORDER BY r.reg_time desc" ,
			countQuery = "SELECT COUNT(*) FROM recipe " ,
		    nativeQuery = true)
	Page<RecipeCategoryDto> getRecipeCategoryOrderList(Pageable pageable , RecipeSearchDto recipeSearchDto);
	
//	모든 레시피와 북마크수 (북마크 높은순)
//	카테고리 페이지
	@Query( value = "select ROW_NUMBER() OVER (ORDER BY COUNT(bm.recipe_id) desc) num ,"
			+ "COUNT(bm.recipe_id) bookmarkCount , r.count count , r.dur_time durTime , r.level level , r.description description , "
			+ "r.title title , r.sub_title subTitle , r.intro intro , r.image_url imageUrl , "
			+ "r.reg_time regTime , r.member_id memberId , r.recipe_id recipeId "
			+ "from recipe r "
			+ "LEFT JOIN book_mark bm ON r.recipe_id = bm.recipe_id "
			+ "GROUP BY r.recipe_id "
			+ "ORDER BY COUNT(bm.recipe_id) desc" ,
			countQuery = "SELECT COUNT(*) FROM recipe " ,
		    nativeQuery = true)
	Page<RecipeCategoryDto> getRecipeCategoryBestList(Pageable pageable , RecipeSearchDto recipeSearchDto);

	
	//순차번호/북마크수/리뷰수/리뷰평점/레시피아이디/조회수/소요시간/메인사진/난이도/부제목/제목/멤버아이디/레시피생성시간/멤버닉네임/멤버메인사진/메인사진여부 y or none/카테고리명
    // 레시피 리뷰 많은 순
	@Query(value = "select "
			+ "    ROW_NUMBER() OVER (ORDER BY rv_count DESC) num ,\r\n"
			+ "    ifnull(bm_count , 0) bookmarkCount,\r\n"
			+ "    ifnull(rv_count , 0) reviewCount,\r\n"
			+ "    COALESCE(rv_avg, 0)  retingAvg,\r\n"
			+ "    r.recipe_id recipeId, r.count count, r.dur_time durTime, r.image_url imageUrl, r.level level, "
			+ "    r.sub_title subTitle, r.title title, r.member_id memberId, r.reg_time regTime, r.intro intro, "
			+ "    m.nickname nickname, "
			+ "    CASE\r\n"
			+ "        WHEN mi.member_id IS NULL THEN 'none'\r\n"
			+ "        ELSE mi.img_url\r\n"
			+ "    END memberImg,\r\n"
			+ "    CASE\r\n"
			+ "        WHEN mi.member_id IS NULL THEN 'none'\r\n"
			+ "        ELSE mi.img_main_ok\r\n"
			+ "    END imgMainOk,\r\n"
			+ "    c.category_enum categoryEnum\r\n"
			+ "FROM recipe r\r\n"
			+ "LEFT JOIN (\r\n"
			+ "    SELECT recipe_id, COUNT(*) AS bm_count\r\n"
			+ "    FROM book_mark\r\n"
			+ "    GROUP BY recipe_id\r\n"
			+ ") bm ON r.recipe_id = bm.recipe_id\r\n"
			+ "LEFT JOIN (\r\n"
			+ "    SELECT recipe_id, COUNT(*) AS rv_count, COALESCE(AVG(reting), 0) AS rv_avg\r\n"
			+ "    FROM review\r\n"
			+ "    GROUP BY recipe_id\r\n"
			+ ") rv ON r.recipe_id = rv.recipe_id\r\n"
			+ "JOIN member m ON r.member_id = m.member_id\r\n"
			+ "LEFT JOIN member_img mi ON m.member_id = mi.member_id AND mi.img_main_ok = 'Y'\r\n"
			+ "JOIN category c ON r.recipe_id = c.recipe_id\r\n"
			+ "ORDER BY rv_count DESC" , 
			countQuery = "SELECT COUNT(*) FROM recipe " ,
		    nativeQuery = true)
	Page<RecipeCategoryDto> getRecipeCategoryBestList2(Pageable pageable , RecipeSearchDto recipeSearchDto);
	
	
	
	

}
