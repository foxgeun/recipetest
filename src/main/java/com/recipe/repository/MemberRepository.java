package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipe.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	//select * from member where email = ?
	Member findByEmail(String email);
	
	//이메일 찾기
	@Query("SELECT m.email FROM Member m WHERE m.phoneNumber = :phoneNumber")
	String findEmailByPhoneNumber(@Param("phoneNumber") String phoneNumber);
	
	Member findByname(String name);
}
