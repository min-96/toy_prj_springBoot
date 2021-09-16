package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.Notice;
import org.hdcd.service.NoticeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public void registerForm(Notice notice, Model model){
        model.addAttribute(notice);

    }
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public String register(Notice notice, RedirectAttributes rttr){
        noticeService.register(notice);
        return "redirect:/notice/list";
    }

    @GetMapping("list")
    public void list(Notice notice,Model model)throws Exception{
        model.addAttribute("list",noticeService.list());
    }

    @GetMapping("/read")
    public void read(Long noticeNo,Model model)throws Exception{
        model.addAttribute(noticeService.read(noticeNo));
    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public void edit(Long noticeNo,Model model)throws Exception{
        model.addAttribute(noticeService.read(noticeNo));
    }
    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(Notice notice,RedirectAttributes rttr)throws Exception{
        noticeService.edit(notice);
        return "redirect:/notice/list";
    }
    @PostMapping("/remove")
    @PreAuthorize("hasRole('ADMIN')")
    public String remove(Long noticeNo,RedirectAttributes rttr)throws Exception{
        noticeService.remove(noticeNo);
        return "redirect:/notice/list";
    }
}
