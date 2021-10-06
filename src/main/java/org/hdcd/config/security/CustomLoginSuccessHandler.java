package org.hdcd.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hdcd.common.controller.util.NetUtils;
import org.hdcd.common.domain.LoginLog;
import org.hdcd.common.service.LoginLogService;
import org.hdcd.domain.CustomUser;
import org.hdcd.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final LoginLogService loginLogService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Member member = customUser.getMember();

        logger.info("Userid = "+ member.getUserId());

        //로그인에 성공한 사용자의 ip정보와 사용자 정보를 로깅처리한다
        String remoteAddr = NetUtils.getIp(request);

        log.info("remoteAddr="+remoteAddr);

        LoginLog loginLog = new LoginLog();

        loginLog.setUserNo(member.getUserNo());
        loginLog.setUserId(member.getUserId());
        loginLog.setRemoteAddr(remoteAddr);

        try{
            loginLogService.register(loginLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
