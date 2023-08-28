package com.recipe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.MemberDto;
import com.recipe.dto.MemberSearchDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.Member;
import com.recipe.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // 쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
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

	//
	@Transactional(readOnly = true)
	public Page<MemberDto> getAdminMemberPage(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		Page<MemberDto> memberPage = memberRepository.getAdminMemberPage(recipeSearchDto, pageable);
		return memberPage;

	}

}
