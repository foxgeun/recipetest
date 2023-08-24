package com.recipe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberBestDto;
import com.recipe.dto.MemberMainDto;
import com.recipe.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	
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
