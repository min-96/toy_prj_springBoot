package org.hdcd.common.controller.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j//로그인을 시도한 클라이언트 ip를 얻는 유틸리티 클래스정의
public class NetUtils {

    public static String getId(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");

        log.info(">>>>>X-FORWARD-FOR"+ip);

        if(ip == null) {
            ip=request.getHeader("Proxy-Client-IP");
            log.info(">>>>>Proxy-Client-Ip"+ip);

        }
        if(ip== null){
            ip= request.getHeader("WL-Proxy-Client_IP");
            log.info(">>>>WL-Proxy-Client_IP"+ip);
        }

        if(ip==null){
            ip= request.getHeader("HTTP_CLIENT_IP");
            log.info(">>>>HTTP_CLIENT_IP"+ip);
        }
        if(ip ==null){
            ip= request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info(">>>>>HTTP_X_FORWARDED_FOR");
        }
        if(ip==null){
            ip= request.getRemoteAddr();
            log.info(">>>>>REsult:IP Address : "+ ip);
        }
        return ip;
    }
}
