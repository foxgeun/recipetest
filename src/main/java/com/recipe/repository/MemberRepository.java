package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import com.recipe.entity.Member;




public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
	//select * from member where email = ?
		Member findByEmail(String email);
		
		@Query(value = "select * from member where member_id = ?1", nativeQuery = true)
		Member getfindmemberbyid(Long id);

}
