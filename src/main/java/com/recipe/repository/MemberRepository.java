package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.dto.MemberMainDto;
import com.recipe.entity.Member;
import com.recipe.entity.Recipe;


public interface MemberRepository extends JpaRepository<Member, Long>  {
	
//	모든 회원의 팔로워수 / 팔로잉수 / 레시피수 /닉네임 / 자기소개 / id (팔로워 많은순으로 정렬)  
//	메인화면
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
