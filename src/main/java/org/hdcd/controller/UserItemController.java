package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.prop.ShopProperties;
import org.hdcd.service.UserItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/useritem")
public class UserItemController {

    private final UserItemService userItemService;

    private final ShopProperties shopProperties;
}
