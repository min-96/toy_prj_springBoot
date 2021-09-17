package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hdcd.domain.Item;
import org.hdcd.prop.ShopProperties;
import org.hdcd.service.ItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ShopProperties shopProperties;



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/register")
    public void registerForm(Model model){

    }
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public String register(Item item, RedirectAttributes rttr) throws Exception{


        MultipartFile ptictureFile = item.getPicture();
        MultipartFile preview = item.getPreview();


        String createdPictureFileName = uploadFile(ptictureFile.getOriginalFilename(),ptictureFile.getBytes());
        String createdPreviceFileName = uploadFile(preview.getOriginalFilename(),preview.getBytes());

        item.setPictureUrl(createdPictureFileName);
        item.setPreviewUrl(createdPreviceFileName);
        itemService.register(item);
        return "redirect:/item/list";
    }


            private String uploadFile(String originalName, byte[]fileData) throws Exception{
                UUID uid = UUID.randomUUID();

                String createdFileName= uid.toString()+"_"+originalName;


                String uploadPath = shopProperties.getUploadPath();
                File target = new File(uploadPath, createdFileName);

                FileCopyUtils.copy(fileData, target);

                return createdFileName;

            }


}
