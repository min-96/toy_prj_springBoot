package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.hdcd.domain.Board;
import org.hdcd.domain.Member;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.dto.PaginationDTO;
import org.hdcd.service.CodeService;
import org.hdcd.service.MemberService;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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


    @GetMapping("/setup")
    public String setupAdminForm(Member member, Model model)throws Exception{
        if(memberService.countAll()==0){
            return "member/setup";
        }
        return "member/setupFailure";
    }

    @PostMapping("/setup")
    public String setupAdmin(Member member,RedirectAttributes attr)throws Exception{
        if(memberService.countAll()==0){
            String inputPassword = member.getUserPwd();
            member.setUserPwd(passwordEncoder.encode(inputPassword));

            member.setFamily("00");
            memberService.setupAdmin(member);
            attr.addFlashAttribute("userName",member.getUserName());
                return "redirect:/member/registerSuccess";
        }
        return "redirect:/member/setupFailure";
    }

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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public void list(@ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model)throws Exception{

        model.addAttribute("list",memberService.Mlist());


//        Page<Member> page = memberService.list(pageRequestVO);
//        model.addAttribute("pgntn" , new PaginationDTO<Member>(page));



    }





    @GetMapping("/read")
    public void read(Long userNo, Model model)throws Exception{
        String classCode = "A02";
        List<CodeLabelValue> familyList = codeService.getCodeList(classCode);
        model.addAttribute("familyList" , familyList);
        model.addAttribute(memberService.read(userNo));
    }


    @GetMapping("/edit")
    public void edit(Long userNo,Model model)throws Exception{
        String classCode = "A02";
        List<CodeLabelValue> familyList = codeService.getCodeList(classCode);
        model.addAttribute("familyList",familyList);
        model.addAttribute(memberService.read(userNo));

    }
    @PostMapping("/edit")
    public String edit(Member member,RedirectAttributes rttr)throws Exception{
        memberService.edit(member);
        return "redirect:/member/list";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/remove")
    public String remove(Long userNo,RedirectAttributes rttr)throws Exception{
        memberService.remove(userNo);
        return "redirect:/member/list";
    }
}
