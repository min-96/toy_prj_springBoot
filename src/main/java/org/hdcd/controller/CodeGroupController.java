package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeGroup;
import org.hdcd.service.CodeGroudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/codegroup")
public class CodeGroupController {

    private final CodeGroudService service;

    @GetMapping("/register")
    public void registerForm(Model model) throws Exception{
        CodeGroup codeGroup = new CodeGroup();

        model.addAttribute(codeGroup);
    }
}
