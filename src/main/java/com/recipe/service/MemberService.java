package com.recipe.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.entity.Member;
import com.recipe.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	//회원가입 데이터를 DB에 저장한다
	public Member saveMember(Member member) {
		validateDuplicateMember(member); //중복체크
		Member savedMember = memberRepository.save(member);
		return savedMember;
	}
	
	//이메일 중복체크
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다");
		}
	}

	@Override //시큐리티 ,DB에서 사용자의 정보를 확인
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) { //DB에 사용자가 없으면
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
					.username(member.getEmail())
					.password(member.getPassword())
					.roles(member.getRole().toString())
					.build();
	}
	
	
}
