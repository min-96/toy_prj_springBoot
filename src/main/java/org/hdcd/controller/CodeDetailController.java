package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeDetail;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.service.CodeDetailService;
import org.hdcd.service.CodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
