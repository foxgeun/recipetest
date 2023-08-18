package com.recipe.service;

import java.util.List;

import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberDto;
import com.recipe.dto.MyPageDto;
import com.recipe.entity.Member;
import com.recipe.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

	private final MemberRepository memberRepository;
	

	@Transactional(readOnly = true)
	public MyPageDto getMemberInfo(Long id){
		
		Member member = memberRepository.findById(id)
										.orElseThrow(EntityNotFoundException::new);
		
		MyPageDto myPageDto = MyPageDto.of(member);
		
		System.out.println(id + "dddddddddddddddddddd");
		System.out.println(myPageDto.getPhoneNumber());

		
		
		
		
		
		return myPageDto;
	}
	
	public Long editMember(MyPageDto myPageDto) throws Exception {
		
		Member member = memberRepository.findById(myPageDto.getId())
										.orElseThrow(EntityNotFoundException::new);
		
		member.editMember(myPageDto);
		
		return member.getId();
	}
	
}
