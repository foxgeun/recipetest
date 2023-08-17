package com.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MngController {
	
	@GetMapping(value = "/memberMng")
	public String memberMng() {
		return "mng/memberMng";
	}
}
