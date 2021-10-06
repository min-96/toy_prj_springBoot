package org.hdcd.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.hdcd.exception.NotEnoughCoinException;
import org.hdcd.exception.NotMyItemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    //코인부족예외처리
    @ExceptionHandler(NotEnoughCoinException.class)
    public String handleNotEnoughCoinException(NotEnoughCoinException ex, WebRequest request)throws Exception{
    return "redirect:/coin/notEnoughCoin";
    }
    //내 상품 예외처리
    @ExceptionHandler(NotMyItemException.class)
    public String handleNotMyItemException(NotMyItemException ex,WebRequest request){
    return "";
    }
    //접근 거부 예외처리
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDinedException(AccessDeniedException ex, HttpServletRequest request, HttpServletResponse response)throws Exception{

    }
    //일반 예외처리
    @ExceptionHandler(Exception.class)
    public String handle(Exception ex){
    return "error/errorCommon";
    }



}
