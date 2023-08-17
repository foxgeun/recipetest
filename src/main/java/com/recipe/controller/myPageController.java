package com.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class myPageController {
	@GetMapping(value="/myPage")
	public String myPage(){
		return "myPage";
	}
}
