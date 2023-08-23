package com.recipe.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipe.dto.MemberSearchDto;
import com.recipe.dto.RecipeDto;
import com.recipe.entity.Member;
import com.recipe.entity.Recipe;
import com.recipe.service.CommentService;
import com.recipe.service.MemberService;
import com.recipe.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MngController {

	private final MemberService memberService;
	private final RecipeService recipeService;
	private final CommentService commentService;

	// 회원관리 페이지
	@GetMapping(value = { "/admin/memberMng", "/admin/memberMng/{page}" })
	public String memberMng(MemberSearchDto memberSearchDto, @PathVariable("page") Optional<Integer> page,
			Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<Member> members = memberService.getAdminMemberPage(memberSearchDto, pageable);

		model.addAttribute("members", members);
		model.addAttribute("memberSearchDto", memberSearchDto);
		model.addAttribute("maxPage", 5);

		return "mng/memberMng";
	}

	// 멤버별 레시피 관리 페이지
	@GetMapping(value = { "/admin/recipeMng", "/admin/recipeMng/{page}" })
	public String recipeMng(MemberSearchDto memberSearchDto, @PathVariable("page") Optional<Integer> page, Model model,
			Long memberId, Long recipeId) {

		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);

		Page<RecipeDto> recipeDtoList = recipeService.getAdminRecipePage(memberSearchDto, pageable, recipeId);

		model.addAttribute("recipes", recipeDtoList);
		model.addAttribute("memberSearchDto", memberSearchDto);
		model.addAttribute("maxPage", 5);

		return "mng/recipeMng";
	}

}