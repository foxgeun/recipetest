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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.recipe.dto.MemberDto;
import com.recipe.dto.SocialMemberDto;
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
	@GetMapping(value = "/members/snsMember")
	public String snsMemberForm(@RequestParam("email") String email, @RequestParam("provider") String provider, 
			@RequestParam("providerId") String providerId, 
			@RequestParam("name") String name, Model model ) {
		SocialMemberDto socialMemberDto = new SocialMemberDto();
		
		socialMemberDto.setEmail(email);
		socialMemberDto.setProvider(provider);
		socialMemberDto.setProviderId(providerId);
		socialMemberDto.setName(name);
	    
		model.addAttribute("socialMemberDto", socialMemberDto);
//		model.addAttribute("email", memberDto.getEmail());
//		model.addAttribute("passwordConfirm", memberDto.getPasswordConfirm());
//		model.addAttribute("provider", memberDto.getProvider());
//		model.addAttribute("providerId", memberDto.getProviderId());
//		model.addAttribute("password", memberDto.getPassword());
		
		System.out.println("aaaaaaaaa111=" + socialMemberDto.getEmail());
		System.out.println("aaaaaaaaa222=" + socialMemberDto.getName());
		System.out.println("aaaaaaaaa333=" + socialMemberDto);
		
		return "member/snsMemberForm";
	}
	
	
	//일반 회원가입 기능
	@PostMapping(value = "/members/newMember")
	public String newMemberForm(@Valid MemberDto memberDto, BindingResult bindingResult, Model model , RedirectAttributes redirectAttributes) {
		
		if (!memberDto.getPassword().equals(memberDto.getPasswordConfirm()) || memberDto.getEmailConfirm2() == "") {
			return "member/newMemberForm";
		}
		
		if (memberDto.getIntroduce() == null || memberDto.getIntroduce() == "") {
			memberDto.setIntroduce("자기소개가 없습니다.");
		}
		
		if (bindingResult.hasErrors()) {
			return "member/newMemberForm";
		}
		try {
			
			Member member = Member.createMember(memberDto, passwordEncoder);
			//System.out.println("member.getname:" + member.getName() + "member.getnickname:" + member.getNickname() + "email:" + member.getEmail());
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
	public String snsMemberForm(@Valid SocialMemberDto socialMemberDto, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "member/snsMemberForm";
		}
		try {
			
				Member member = Member.createSnsMember(socialMemberDto);
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
		model.addAttribute("loginErrorMsg", "로그인 실패 아이디 또는 비밀번호를 확인해주세요");
		return "member/loginForm";
	}

	// 아이디/비밀번호찾기
	@GetMapping(value = "/members/findIDPW")
	public String findIDPW(Model model) {
		return "member/findIDPW";
	}
	
}
