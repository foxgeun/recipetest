package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.recipe.entity.Follow;
import com.recipe.entity.Member;

public interface FollowRepository extends JpaRepository<Follow, Long>{
	
	@Modifying 
	@Query(value= "insert into Follow(member_id, from_member) VALUES(?1,?2)", nativeQuery = true)
	void saveFollow(Member toMember,String fromMember);
	
	 void deleteByToMemberAndFromMember(Member toMember, Member fromMember);
}
