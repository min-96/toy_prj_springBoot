package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.CustomUser;
import org.hdcd.domain.Member;
import org.hdcd.prop.ShopProperties;
import org.hdcd.service.UserItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/useritem")
public class UserItemController {

    private final UserItemService userItemService;

    private final ShopProperties shopProperties;


    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    public void list(Model model, Authentication auth)throws Exception{

        CustomUser customerUser = (CustomUser) auth.getPrincipal();
        Member member = customerUser.getMember();

        Long userNo = member.getUserNo();
        model.addAttribute("list",userItemService.list(userNo));
    }
}
