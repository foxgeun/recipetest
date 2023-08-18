package com.recipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recipe.dto.MemberDto;
import com.recipe.dto.MemberSearchDto;
import com.recipe.entity.Member;

public interface MemberRepositoryCustom {

	Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);

//
//	Page<MemberDto> getMemberPage(MemberSearchDto memberSearchDto, Pageable pageable);
}
