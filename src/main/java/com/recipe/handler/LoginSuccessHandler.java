package com.recipe.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.recipe.entity.Member;
import com.recipe.oauth.PrincipalDetails;
import com.recipe.repository.MemberRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
		
		@Autowired
		private MemberRepository memberRepository;

	    
	    
		
	    @Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			
	    	   System.out.println("LoginSuccessHandler is called.");
	    	   PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
	           Member member = principal.getMember();
	           
	           
	           String targetUrl = determineTargetUrl(member);
	           response.sendRedirect(targetUrl);
	    	   

//	    	    if (authentication instanceof OAuth2AuthenticationToken) {
//	    	        OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();
//	    	        System.out.println("oauthUser===" + oauthUser);
//	    	        if (oauthUser instanceof PrincipalDetails) {
//	    	            PrincipalDetails userDetails = (PrincipalDetails) oauthUser;
//	    	            String email = userDetails.getEmail(); // Use getEmail from PrincipalDetails
//	    	            System.out.println("email====" + email);
//	    	            String name = userDetails.getName();
//	    	            String password = userDetails.getPassword();
//	    	            String provider = userDetails.getProvider();
//	    	            String providerId = userDetails.getProviderId();
//	    	            
//	    	            Member member = memberRepository.findByEmail(email);
//	    	            System.out.println("member====" + member);
//	    	            MemberDto memberDto = new MemberDto();
//	    	            //name password 추가
//	    	            //dto에 provide 등 추가
//	                    memberDto.setEmail(email);
//	                    memberDto.setName(name);
//	                    memberDto.setPassword(password);
//	                    memberDto.setPasswordConfirm(password);
//	                    memberDto.setProvider(provider);
//	                    memberDto.setProviderId(providerId);
//	                    
//	                    System.out.println("provider=====" + provider);
//	                    System.out.println("providerId=====" + providerId);
//	                    
//	    	            if (member == null) {
//	    	                // 간편 로그인 성공시 추가 정보를 받기위해
//	    	            	request.setAttribute("memberDtos", memberDto);
//	    	            	String redirectUrl = "/members/snsMember?email=" + URLEncoder.encode(memberDto.getEmail(), "UTF-8") + "&passwordConfirm=" + URLEncoder.encode(memberDto.getPasswordConfirm(), "UTF-8");
//	    	            	response.sendRedirect(redirectUrl);
//	    	            } else {
//	    	                // 실패시
//	    	                response.sendRedirect("/"); 
//	    	            }
//	    	        }
//
//	    	    }
	    }
	    
	    
	    private String determineTargetUrl(Member member) {
	    	
	    	Member findMember = memberRepository.findByEmail(member.getEmail());
	    	
	        if (findMember == null) {
	            return "/login/joinForm";
	        } else {
	            return "/";
	        }
	    }
}
