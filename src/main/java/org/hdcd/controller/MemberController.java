package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.service.CodeService;
import org.hdcd.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {


    private final MemberService memberService;

    private final CodeService codeService;

    private final PasswordEncoder passwordEncoder;

}
