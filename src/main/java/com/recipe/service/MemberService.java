package com.recipe.service;

import java.util.ArrayList;
import java.util.List;

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
import com.recipe.dto.RecipeDto;
import com.recipe.entity.Member;
import com.recipe.entity.Recipe;
import com.recipe.repository.MemberRepository;
import com.recipe.repository.RecipeListRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional // 쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final RecipeListRepository recipeListRepository;

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

	// 레시피 가져오기
	@Transactional(readOnly = true) // 트랙잰션 읽기 전용(변경감지 수해하지 않음) ->성능 향상
	public MemberDto getRecipeMng(Long recipeId) {

		// 1.item_img 테이블의 이미지를 가져온다.
		List<Recipe> recipeList = recipeListRepository.findByRecipeId(recipeId);

		// ItemImg 엔티티 객체 -> ItemImgDto로 변환
		List<RecipeDto> recipeDtoList = new ArrayList<>();
		for (Recipe recipe : recipeList) {
			RecipeDto recipeDto = RecipeDto.of(recipe);
			recipeDtoList.add(recipeDto);
		}

		// 2.recipe테이블에 있는데이터를 가져온다.
		Member member = memberRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

		// Recipe 엔티티 객체 -> dto로 변환
		MemberDto memberDto = MemberDto.of(member);

		// 3.ItemFormDto에 이미지 정보(itemImgDtoList)를 넣어준다.
		memberDto.setRecipeDtoList(recipeDtoList);

		return memberDto;

	}

	@Transactional(readOnly = true)
	public Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable) {
		Page<Member> memberPage = memberRepository.getAdminMemberPage(memberSearchDto, pageable);
		return memberPage;

	}

}
