package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;
import org.dom4j.rule.Mode;
import org.hdcd.domain.CodeDetail;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.service.CodeDetailService;
import org.hdcd.service.CodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/codedetail")
public class CodeDetailController {

    private final CodeDetailService codeDetailService;

    private final CodeService codeService;


    @GetMapping("/register")
    public void registerForm(Model model) throws Exception{
        CodeDetail codeDetail = new CodeDetail();
        model.addAttribute(codeDetail);

        List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();

        model.addAttribute("groupCodeList",groupCodeList);

    }

    @PostMapping("/register")
    public String register(CodeDetail codeDetail, RedirectAttributes rttr)throws Exception{
        codeDetailService.register(codeDetail);
        return "redirect:/codedetail/list";
    }

    @GetMapping("/list")
    public void list(Model model)throws  Exception{
        model.addAttribute("list",codeDetailService.list());
    }

    @GetMapping("/read")
    public void read(CodeDetail codeDetail, Model model)throws Exception{
        model.addAttribute("codeDetail",codeDetailService.read(codeDetail));

        List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
        model.addAttribute("groupCodeList",groupCodeList);

    }
    @GetMapping("edit")
    public void edit(CodeDetail codeDetail,Model model)throws Exception{
        model.addAttribute(codeDetailService.read(codeDetail));

        List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
        model.addAttribute("groupCodeList",groupCodeList);

    }
    @PostMapping("edit")
    public String edit(CodeDetail codeDetail,RedirectAttributes rttr)throws Exception{
        codeDetailService.edit(codeDetail);
        return "redirect:/codedetail/list";
    }

}
