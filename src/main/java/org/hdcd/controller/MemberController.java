package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Member;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.service.CodeService;
import org.hdcd.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {


    private final MemberService memberService;

    private final CodeService codeService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public void register(Model model){
        Member member= new Member();
        model.addAttribute("member",member);
        String classCode = "A02";
        List<CodeLabelValue> familyList = codeService.getCodeList(classCode);

        model.addAttribute("familyList",familyList);
    }

    @PostMapping("/register")
    public String register(@Validated Member member, BindingResult result, Model model, RedirectAttributes rttr)throws Exception{
        if(result.hasErrors()){
            String classCode="A02";
            List<CodeLabelValue> familyList = codeService.getCodeList(classCode);
            model.addAttribute("familyList",familyList);
        return "member/list";
        }
        String inputPassword = member.getUserPwd();
        member.setUserPwd(passwordEncoder.encode(inputPassword));
        memberService.register(member);
        rttr.addFlashAttribute("userName",member.getUserName());
        return "redirect:/member/registerSuccess";
    }
    @GetMapping("/registerSuccess")
    public void registerSuccess(Model model)throws Exception{

    }
}
