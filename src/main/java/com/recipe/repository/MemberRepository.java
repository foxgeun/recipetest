package com.recipe.repository;

import java.util.List;
import java.util.Optional;

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
	
	
	@Query(value = "select * from member where member_id = ?1", nativeQuery = true)
	Member getfindmemberbyid(Long id);
	
	Optional<Member> findByEmail(String email);
}
