package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CodeGroup;
import org.hdcd.service.CodeGroudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PostMapping("/register")
    public String register(CodeGroup codeGroup,RedirectAttributes rttr)throws Exception{
        service.register(codeGroup);

        rttr.addFlashAttribute("msg","Success");

       // RedirectAttributes가 제공하는 메소드 addFlashAttribute()
      //  addFlashAttribute() 는 리다이렉트 직전 플래시에 저장하는 메소드다. 리다이렉트 이후에는 소멸한다.
        return "redirect:/codegroup/list";
    }

    @GetMapping("list")
    public void list(Model model)throws Exception{
        model.addAttribute("list",service.list());
    }

    @GetMapping("/read")
    public void read(String groupCode,Model model) throws Exception{
        model.addAttribute(service.read(groupCode));

    }

    @GetMapping("/edit")
    public void edit(String groupCode,Model model) throws Exception{

            model.addAttribute(service.read(groupCode));
    }

    @PostMapping("/edit")
    public String edit(CodeGroup codeGroup,RedirectAttributes rttr) throws Exception{

        service.edit(codeGroup);

        return "redirect:/codegroup/list";
    }
}
