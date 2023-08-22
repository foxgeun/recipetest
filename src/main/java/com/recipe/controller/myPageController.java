package com.recipe.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.dto.MemberDto;
import com.recipe.dto.MyPageDto;
import com.recipe.entity.Member;
import com.recipe.repository.MemberRepository;
import com.recipe.service.MyPageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class myPageController {
	
	private final MyPageService myPageService;
	private final MemberRepository memberRepository;
	
	//수정페이지 보여주기
	@GetMapping(value="/myPage/{id}")
	public String myPage(@PathVariable("id") Long id,Model model) {
		Member myPageDto = memberRepository.getfindmemberbyid(id);

		model.addAttribute("myPageDto",myPageDto);
		
		System.out.println(myPageDto.getId());
		
		return "myPage";
		
	}
	
	
	//회원정보수정하기 
	@PostMapping(value = "/myPage/{id}")
	public String editMember(@PathVariable("id") Long id, @Valid MyPageDto myPageDto, Model model,@RequestParam("imgFile") MultipartFile imgFile) {
		
		try {
		       // 이미지 파일 업로드 처리
	        // imgUrl을 MyPageDto에 설정 


			myPageService.editMember(myPageDto,imgFile);
			System.out.println(myPageDto.getImgUrl() + "imgUrl");
			System.out.println(myPageDto.getImgName());
			
			System.out.println(imgFile + "imgFile");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/myPage/{id}";
	}
}
