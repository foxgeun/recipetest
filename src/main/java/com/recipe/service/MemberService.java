package com.recipe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberBestDto;
import com.recipe.dto.MemberMainDto;
import com.recipe.entity.Follow;
import com.recipe.entity.Member;
import com.recipe.repository.FollowRepository;
import com.recipe.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final FollowRepository followRepository;
	
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
	
	
	public String login(String member , String role) {
		
		Member memberCk = memberRepository.findBynickname(member);
		
		if("USER".equals(memberCk.getRole())) {
			role = "USER";
		}else {
			role = "ADMIN";
		}
		
		return role;
	}
	
	
	
	public void followReg(Long id) {
		
		/*
		 * String id5 = "5" ;
		 * 
		 * Long memberId = Long.parseLong(id5);
		 * 
		 * Member member = memberRepository.findById(memberId).orElseThrow();
		 * 
		 * Follow followTest =
		 * followRepository.findByMemberIdAndToMember(member.getId(),memberId);
		 * 
		 * if(followTest == null) { Follow follow = new Follow();
		 * follow.setMember(member); follow.setToMember(id);
		 * followRepository.save(follow); System.out.println("저장되었습니다"); }
		 * 
		 * System.out.println("이미 팔로우 되있습니다");
		 */
		
	}
	
	
	
}
