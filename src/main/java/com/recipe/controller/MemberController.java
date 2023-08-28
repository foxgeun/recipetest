package com.recipe.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import com.recipe.dto.MemberDto;
import com.recipe.dto.SocialMemberDto;
import com.recipe.entity.Member;
import com.recipe.service.FileService;
import com.recipe.service.MemberService;
import com.recipe.service.RamdomPassword;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	private final RamdomPassword randomPassword; 
	private final FileService fileService;
	private String profileImgLocation = "C:/recipe/profile";

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
		System.out.println("aaaaaaaaaaaaa==== " + memberDto.getImgUrl());
		
		//회원가입 에러처리
		if (!memberDto.getPassword().equals(memberDto.getPasswordConfirm()) || memberDto.getEmailConfirm2() == "") {
			return "member/newMemberForm";
		}
		
		//자기소개 기본값 넣기
		if (memberDto.getIntroduce() == null || memberDto.getIntroduce() == "") {
			memberDto.setIntroduce("자기소개가 없습니다.");
		}
		
		String oriImgName = memberDto.getImgUrl();
		memberDto.setOriImgName(oriImgName);
		
		System.out.println("oriImgName====" + oriImgName);
		String imgUrl = "";
		String imgName = "";
		
		//프로필 사진처리
		if(!StringUtils.isEmpty(oriImgName)) {
			try {
				imgName = fileService.profileImgFile(profileImgLocation, oriImgName ,oriImgName.getBytes());
				System.out.println("imgName========" + imgName);
				imgUrl = "/img/profile/" + imgName;
				System.out.println("imgName====" + imgUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			memberDto.setImgName(imgName);
			memberDto.setImgUrl(imgUrl);
			
		} else {
			
			// oriImgName이 비어있거나 null일 때 처리 고양이 이미지 추가
		    imgName = "이젠 해먹자.svg"; 
		    imgUrl = "/img/profile/" + imgName;
		    System.out.println("기본값 name-----" + imgName);
		    System.out.println("기본값 프로필 이미지===" + imgUrl);
		    memberDto.setImgName(imgName);
		    memberDto.setImgUrl(imgUrl);
		}
		
		//에러처리
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
		
		if(socialMemberDto.getIntroduce() == null || socialMemberDto.getIntroduce() == "") {
			socialMemberDto.setIntroduce("자기소개가 없습니다.");
		}
		
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

	// 아이디/비밀번호찾기 페이지
	@GetMapping(value = "/members/findIDPW")
	public String findIDPW() {
		return "member/findIDPW";
	}
	
	/*
	 * 
	 * 
	 * @GetMapping(value = "/findpw") public String search_ps(Model model) {
	 * model.addAttribute("loginFormDto", new MemberDto()); return
	 * "login/findPwForm"; }
	 */
	
	// 비밀번호 찾고 난수생성기로 랜덤비밀번호 생성 , 아이디/비밀번호 찾기
	@PostMapping(value = "/findPw")
	@ResponseBody
	public HashMap<String, String> memberps(@RequestBody Map<String, Object> psdata, Principal principal) {
		String email = (String) psdata.get("email");
		HashMap<String, String> msg = new HashMap<>();
		
		String memberEmail = randomPassword.findMember(email);
		
		// email이 DB에 없는 경우
	    if (memberEmail == null) {
	        msg.put("error", "입력하신 이메일 주소가 존재하지 않습니다.");
	        return msg;
	    }
		
		String ramdomps = randomPassword.getRamdomPassword(12); // 임시 비밀번호 12자리생성
		System.out.println("ramdomps===" + ramdomps);
		
		//임시 비밀번호를 DB에 저장해서 바꿈 => 임시 비밀번호로 저장해야 로그인성공
		String password = randomPassword.updatePassword(ramdomps, email, passwordEncoder);
		
		String emailBody = 
				"<h3>요청하신 임시 비밀번호입니다.</h3>" + 
                "<h1>" + ramdomps + "</h1>" + 
                "<h3>감사합니다.</h3>";
		
		randomPassword.sendEmail(email, "임시 비밀번호", emailBody);
		
		String asd1 = "이메일로 임시 비밀번호가 발송되었습니다.";
		String asd2 = "임시 비밀번호를 사용해 로그인 해주십시오.";
		msg.put("message1", asd1);
		msg.put("message2", asd2);
		return msg;
	}
	
	//이메일 찾기
	@PostMapping(value = "/findEmail")
	public ResponseEntity<Map<String, String>> findEmail(@RequestBody MemberDto memberDto) {
		
		String memberEmail = memberService.findEmail(memberDto.getPhoneNumber());
		System.out.println("memberEmail====" + memberEmail);
		
		Map<String, String> response = new HashMap<>();
		response.put("memberEmail", memberEmail);
		System.out.println("response====" + response);
		
		return ResponseEntity.ok(response);
		
	}
}
