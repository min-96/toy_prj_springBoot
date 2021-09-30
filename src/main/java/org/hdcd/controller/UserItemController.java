package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.hdcd.domain.CustomUser;
import org.hdcd.domain.Member;
import org.hdcd.domain.UserItem;
import org.hdcd.prop.ShopProperties;
import org.hdcd.service.UserItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

    @GetMapping("/read")
    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    public void read(Long userItemNo,Model model)throws Exception{
        model.addAttribute(userItemService.read(userItemNo));
    }

    @ResponseBody
    @GetMapping("/download")
    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    public ResponseEntity<byte[]> download(Long userItemNo, Authentication authentication)throws Exception{
        UserItem userItem= userItemService.read(userItemNo);

        String fullName = userItem.getPictureUrl();

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try{
            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream(shopProperties.getUploadPath()+ File.separator+fullName);
            String fileName = fullName.substring(fullName.indexOf("_")+1);

            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition","attachment; filename=\""+new String(fileName.getBytes(StandardCharsets.UTF_8),"ISO-8859-1")+"\"");

            entity =new ResponseEntity<>(IOUtils.toByteArray(in),headers, HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
            entity= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }finally {
            in.close();
        }
        return entity;
    }
}
