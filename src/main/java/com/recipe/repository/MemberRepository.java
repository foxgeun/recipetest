package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipe.dto.MemberDto;
import com.recipe.dto.MyPageDto;
import com.recipe.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>
										  {
	
//	@Query("select o from member o where o.member.member_id = :id")
//	Member getmember(@Param("id") Long id);
}
