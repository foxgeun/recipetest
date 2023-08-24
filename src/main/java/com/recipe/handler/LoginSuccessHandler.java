package com.recipe.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.recipe.entity.Member;
import com.recipe.oauth.PrincipalDetails;
import com.recipe.repository.MemberRepository;

import groovy.util.logging.Slf4j;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
		
	  private final MemberRepository memberRepository;

	    @Autowired
	    public LoginSuccessHandler(MemberRepository memberRepository) {
	        this.memberRepository = memberRepository;
	    }
		
	    @Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			
	    	   System.out.println("LoginSuccessHandler is called.");

	    	    if (authentication instanceof OAuth2AuthenticationToken) {
	    	        OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();
	    	        System.out.println("oauthUser===" + oauthUser);
	    	        if (oauthUser instanceof PrincipalDetails) {
	    	            PrincipalDetails userDetails = (PrincipalDetails) oauthUser;
	    	            String email = userDetails.getEmail(); // Use getEmail from PrincipalDetails
	    	            System.out.println("email====" + email);
	    	            Member member = memberRepository.findByEmail(email);
	    	            System.out.println("member====" + member);
	    	            if (member != null) {
	    	                // 간편 로그인 성공시 추가 정보를 받기위해
	    	                response.sendRedirect("/members/newMember");
	    	            } else {
	    	                // 실패시
	    	                response.sendRedirect("/"); 
	    	            }
	    	        }

	    	    }
	    }
}
