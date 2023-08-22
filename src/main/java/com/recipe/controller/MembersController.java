/*
 * package com.recipe.controller;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping;
 * 
 * import com.recipe.entity.Member;
 * 
 * import jakarta.servlet.http.HttpSession; import
 * lombok.RequiredArgsConstructor;
 * 
 * @Controller
 * 
 * @RequiredArgsConstructor public class MembersController {
 * 
 * @GetMapping("/members/newMembers") public String showStep1Page(Model model) {
 * model.addAttribute("member", new Member()); return "member/agreeForm"; //
 * step1.html을 렌더링 }
 * 
 * @PostMapping("/members/newMembers") public String
 * processStep1(@ModelAttribute("member") Member member, HttpSession session) {
 * session.setAttribute("member", member); // 세션에 첫 번째 페이지 정보 저장 return
 * "redirect:/member/newMemberForm"; // 두 번째 페이지로 리다이렉트 }
 * 
 * 
 * 
 * @GetMapping("/members/newMember") public String showStep2Page(Model model) {
 * model.addAttribute("member", new Member()); return "/member/newMemberForm";
 * // step2.html을 렌더링 }
 * 
 * @PostMapping("/members/newMember") public String
 * processStep2(@ModelAttribute("member") Member member, HttpSession session) {
 * Member firstPageInfo = (Member) session.getAttribute("member"); // 세션에서 첫 번째
 * 페이지 정보 가져오기 member.setServiceOk(firstPageInfo.getServiceOk());
 * member.setPrivateOk(firstPageInfo.getPrivateOk());
 * member.setPromotionOk(firstPageInfo.getPromotionOk()); // 이제 member 객체에 두 번째
 * 페이지 정보도 설정됨 // member 객체를 저장하거나 다른 처리 작업 수행 return "redirect:/"; // 완료 페이지로
 * 리다이렉트 } }
 */