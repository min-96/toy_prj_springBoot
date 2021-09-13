package org.hdcd.security;

import lombok.extern.slf4j.Slf4j;
import org.hdcd.domain.CustomMember;
import org.hdcd.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    //로그인 성공처리자 메서드
    //로그인한 사용자의 IP 정보 저장하기
    //로그인 이전에 요청했던 URL이 있으면, 로그인이 성공한 뒤, 그 URL로 Redirect 해 주


   // Authentication(인증) : 'A'라고 주장하는 주체(user, subject, principal)가 'A'가 맞는지 확인하는 것
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomMember customUser = (CustomMember)authentication.getPrincipal();
        Member member = customUser.getMember();

        log.info("Userid = "+member.getUserId());
        super.onAuthenticationSuccess(request, response, authentication);
    }


}
