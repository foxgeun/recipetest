package com.recipe.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recipe.dto.MemberDto;
import com.recipe.entity.Member;
import com.recipe.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	// 로그인 화면
	@GetMapping(value = "/members/login")
	public String loginMember() {
		return "member/loginForm";
	}

	// 약관 동의
	@GetMapping(value = "/members/agree")
	public String agreeMember() {
		return "member/agreeForm";
	}

	// 레시피 등록
	@GetMapping(value = "/recipe/new")
	public String newRecipe() {
		return "recipe/new";
	}

	//일반 회원가입 화면
	@GetMapping(value = "/members/newMember")
	public String newMemberForm(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "member/newMemberForm";
	}
	
	//sns 회원가입 화면
	@PostMapping(value = "/members/snsMember")
	public String snsMemberForm(@RequestParam("email") String email, @RequestParam("passwordConfirm") String passwordConfirm, 
			@RequestParam("provider") String provider, @RequestParam("providerId") String providerId, Model model ) {
		MemberDto memberDto = new MemberDto();
		
		memberDto.setEmail(email);
	    memberDto.setPasswordConfirm(passwordConfirm);
	    memberDto.setPassword(passwordConfirm);
	    memberDto.setProvider(provider);
	    memberDto.setProviderId(providerId);
	    
		model.addAttribute("memberDto", memberDto);
		model.addAttribute("email", memberDto.getEmail());
		model.addAttribute("passwordConfirm", memberDto.getPasswordConfirm());
		System.out.println("aaaaaaaaa111=" + memberDto.getEmail());
		System.out.println("aaaaaaaaa222=" + memberDto.getPasswordConfirm());
		System.out.println("aaaaaaaaa333=" + memberDto);
		
		return "member/snsMemberForm";
	}
	
	
	//일반 회원가입 기능
	@PostMapping(value = "/members/newMember")
	public String newMemberForm(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		
		if (!memberDto.getPassword().equals(memberDto.getPasswordConfirm())) {
			return "member/newMemberForm";
		}
		
		if (bindingResult.hasErrors()) {
			return "member/newMemberForm";
		}
		try {
			
				Member member = Member.createMember(memberDto, passwordEncoder);
				System.out.println("member.getname:" + member.getName() + "member.getnickname:" + member.getNickname() + "email:" + member.getEmail());
				memberService.saveMember(member);
			
		} catch (IllegalStateException e) {
			// 회원가입 실패시
			model.addAttribute("errorMessage", e.getMessage());
			return "member/newMemberForm";
		}
		// 회원가입 성공시
		return "redirect:/";
	}
	
	
	//sns 회원가입 기능
	@PostMapping(value = "/members/snsMember")
	public String snsMemberForm(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
		
		if (!memberDto.getPassword().equals(memberDto.getPasswordConfirm())) {
			return "member/snsMemberForm";
		}
		
		if (bindingResult.hasErrors()) {
			return "member/snsMemberForm";
		}
		try {
			
				Member member = Member.createMember(memberDto, passwordEncoder);
				System.out.println("member.getname:" + member.getName() + "member.getnickname:" + member.getNickname() + "email:" + member.getEmail());
				memberService.saveMember(member);
			
		} catch (IllegalStateException e) {
			// 회원가입 실패시
			model.addAttribute("errorMessage", e.getMessage());
			return "member/snsMemberForm";
		}
		// 회원가입 성공시
		return "redirect:/";
	}

	// 로그인 실패
	@GetMapping(value = "/members/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
		return "member/loginForm";
	}

	// 아이디/비밀번호찾기
	@GetMapping(value = "/members/findIDPW")
	public String findIDPW(Model model) {
		return "member/findIDPW";
	}
	
}
