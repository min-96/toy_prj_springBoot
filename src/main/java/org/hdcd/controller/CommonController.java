//package org.hdcd.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//@Slf4j
//public class CommonController {
//
//
//    @GetMapping("/error/errorCommon")
//    public void handleCommonError(){
//        log.info("errorCommon");
//    }
//
//    @GetMapping("/error/accessError")
//    public void accessDenined(Authentication auth, Model model){
//        log.info("accessDenied"+auth);
//        model.addAttribute("msg" , "Access Denied");
//
//    }
//}
