package com.recipe.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberSearchDto;
import com.recipe.entity.Member;
import java.util.List;

import com.recipe.dto.MemberBestDto;
import com.recipe.dto.MemberMainDto;
import com.recipe.repository.MemberRepository;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;


	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// 사용자가 입력한 email이 DB에 있는지 쿼리문을 사용한다.
		Member member = memberRepository.findByEmail(email);

		if (member == null) { // 사용자가 없다면
			throw new UsernameNotFoundException(email);
		}

		// 사용자가 있다면 DB에서 가져온 값으로 userDetails 객체를 만들어서 반환
		return User.builder().username(member.getEmail()).password(member.getPassword())
				.roles(member.getRole().toString()).build();
	}

	@Transactional(readOnly = true)
	public Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable) {
		Page<Member> memberPage = memberRepository.getAdminMemberPage(memberSearchDto, pageable);
		return memberPage;

	}
	
	@Transactional(readOnly = true)
	public List<MemberMainDto> getMemberBestList() {
		List<MemberMainDto> getMemberBestList = memberRepository.getMemberBestList();
		return getMemberBestList;
		
	}
	
	@Transactional(readOnly = true)
	public List<MemberBestDto> getRankMemberList() {
		List<MemberBestDto> getRankMemberList = memberRepository.getRankMemberList();
		return getRankMemberList;
	}
	

}
