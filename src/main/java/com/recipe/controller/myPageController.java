package com.recipe.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.recipe.dto.MemberDto;
import com.recipe.dto.MyPageDto;
import com.recipe.service.MyPageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class myPageController {
	
	private final MyPageService myPageService;
	
	@GetMapping(value="/myPage/{id}")
	public String myPage(@PathVariable("id") Long id,Model model) {
		MyPageDto myPageDto = myPageService.getMemberInfo(id);

		model.addAttribute("myPageDto",myPageDto);
		
		return "myPage";
		
	}
	
	@PostMapping(value = "/myPage/{id}")
	public String editMember(@Valid MyPageDto myPageDto, Model model) {
		
		try {
			myPageService.editMember(myPageDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
