package com.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QAController {
	// 문의하기
	@GetMapping(value = "/board/qa")
	public String qa() {
		return "board/qa";
	}
}
