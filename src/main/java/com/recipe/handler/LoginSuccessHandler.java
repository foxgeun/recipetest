package com.recipe.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.recipe.entity.Member;
import com.recipe.oauth.PrincipalDetails;
import com.recipe.repository.MemberRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
		
	  private final MemberRepository memberRepository;

	    @Autowired
	    public LoginSuccessHandler(MemberRepository memberRepository) {
	        this.memberRepository = memberRepository;
	    }
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			
			
			PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
			String username = userDetails.getUsername();
			
			
			OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
		    OAuth2User oauthUser = oauthToken.getPrincipal();
		    String email = (String) oauthUser.getAttributes().get("email");
		    
		    
		    Member member = memberRepository.findByEmail(email);
		    
		    if (member == null) {
		        // 로그인한 이메일이 DB에 없는 경우
		        response.sendRedirect("/members/newMember");
		    } else {
		        // 로그인한 이메일이 DB에 있는 경우
		        response.sendRedirect("/"); // 로그인 성공 후 이동할 페이지
		    }
		}
}
