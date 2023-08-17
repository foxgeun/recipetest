package com.recipe.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	// 로그인 화면
	@GetMapping(value="/members/login")
	public String loginMember() {
		return "member/loginForm";
	}
	
	// 약관 동의
	@GetMapping(value="/members/agree")
	public String agreeMember() {
		return "member/agreeForm";
	}
	
	//레시피 등록
	@GetMapping(value="/recipe/new")
	public String newRecipe() {
		return "recipe/new";
	}
	
	// 회원가입 화면
	@GetMapping(value="/members/newMember")
	public String newMemberForm(Model model) {
		return "member/newMemberForm";
	}
	
	// 아이디/비밀번호찾기
	@GetMapping(value="/members/findIDPW")
	public String findIDPW(Model model) {
		return "member/findIDPW";
	}
}
