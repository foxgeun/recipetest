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

import com.recipe.dto.MemberSearchDto;
import com.recipe.dto.RecipeDto;
import com.recipe.entity.Member;
import com.recipe.entity.Recipe;
import com.recipe.repository.MemberRepository;
import com.recipe.repository.RecipeListRepository;
import com.shopmax.dto.ItemFormDto;
import com.shopmax.dto.ItemImgDto;
import com.shopmax.entity.Item;
import com.shopmax.entity.ItemImg;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional // 쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
public class RecipeService implements UserDetailsService {

	private final RecipeListRepository recipeListRepository;
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

	// 상품 가져오기
	@Transactional(readOnly = true) // 트랙잰션 읽기 전용(변경감지 수해하지 않음) ->성능 향상
	public RecipeDto getRecipeDtl(Long itemId) {

		// ItemImg 엔티티 객체 -> ItemImgDto로 변환
		List<ItemImgDto> itemImgDtoList = new ArrayList<>();
		for (ItemImg itemImg : itemImgList) {
			ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
			itemImgDtoList.add(itemImgDto);
		}

		// 2.item테이블에 있는데이터를 가져온다.
		Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

		// Item 엔티티 객체 -> dto로 변환
		ItemFormDto itemForDto = ItemFormDto.of(item);

		// 3.ItemFormDto에 이미지 정보(itemImgDtoList)를 넣어준다.
		itemForDto.setItemImgDtoList(itemImgDtoList);
		return itemForDto;

	}

}
