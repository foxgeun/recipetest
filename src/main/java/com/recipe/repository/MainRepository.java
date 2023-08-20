package com.recipe.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.dto.MemberMainDto;
import com.recipe.dto.RecipeMainDto;
import com.recipe.entity.Recipe;


public interface MainRepository extends JpaRepository<Recipe , Long> , 
										RecipeRepositoryCustom{
	
//	조회수 가장 높은순으로 모든레시피 가져옴 (제한 5개)
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			+ "order by count desc limit 5" 
			, nativeQuery = true)
	List<RecipeMainDto> getRecipeBestList();

//	최근 등록순으로 모든레시피 가져옴 (제한 5개)
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			+ "order by dur_time desc limit 5" 
			, nativeQuery = true)
	List<RecipeMainDto> getRecipeNewList();

//	모든 레시피
	@Query ( value = "select recipe.recipe_id id , recipe.title title , recipe.sub_title subTitle , "
			+ "recipe.intro intro , recipe.dur_time durTime , "
			+ "recipe.level level , recipe.count count "
			+ "from recipe "
			, nativeQuery = true)
	List<RecipeMainDto> getRecipeTotalList();
	
//	모든 회원의 팔로워수 / 팔로잉수 / 레시피수 /닉네임 / 자기소개 / id (팔로워 많은순으로 정렬)  
	@Query ( value = "SELECT " +
			"ROW_NUMBER() OVER (ORDER BY followers.followers_count DESC) num, "+
			"m.member_id id, " +
			"m.nickname nickname, " +
			"ifnull(img.img_url , 'none') imgUrl, " +
            "ifnull(followers.followers_count,0) followCount, " +
            "ifnull(followings.followings_count,0) followingCount, " +
            "ifnull(recipes.recipes_count,0) recipeCount, " +
            "ifnull(img.img_name , 'none') imgName, " +
            "ifnull(img.img_main_ok , 'none') imgMainOk " +
            "FROM member m " +
            "LEFT JOIN (SELECT to_member, COUNT(member_id) AS followers_count FROM follow GROUP BY to_member) followers ON m.member_id = followers.to_member " +
            "LEFT JOIN (SELECT member_id, COUNT(to_member) AS followings_count FROM follow GROUP BY member_id) followings ON m.member_id = followings.member_id " +
            "LEFT JOIN (SELECT member_id, COUNT(recipe_id) AS recipes_count FROM recipe GROUP BY member_id) recipes ON m.member_id = recipes.member_id " +
            "LEFT JOIN member_img img ON m.member_id = img.member_id " +
            "WHERE img.img_main_ok = 'Y' " +
            "OR img.member_id IS NULL " +
            "ORDER BY followers.followers_count DESC"
			, nativeQuery = true)
	List<MemberMainDto> getMemberBestList();
		

}
