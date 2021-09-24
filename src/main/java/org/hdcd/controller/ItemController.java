package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.hdcd.domain.CustomUser;
import org.hdcd.domain.Item;
import org.hdcd.domain.Member;
import org.hdcd.prop.ShopProperties;
import org.hdcd.service.ItemService;
import org.hdcd.service.MemberService;
import org.hdcd.service.UserItemService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ShopProperties shopProperties;
    private final MemberService memberService;
    private final UserItemService userItemService;
    private final MessageSource messageSource;



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/register")
    public void registerForm(Item item,Model model){


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


    @GetMapping("/list")
    public void list(Item item,Model model)throws Exception{
    List<Item> itemList = itemService.list();

    model.addAttribute(item);

    model.addAttribute("itemList",itemList);
    }

    @GetMapping("/read")
    public void read(Long itemId,Model model)throws Exception{
        model.addAttribute("item", itemService.read(itemId));
    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public void edit(Long itemId,Model model) throws Exception{
        model.addAttribute("item",itemService.read((itemId)));
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(Item item, RedirectAttributes rttr) throws Exception{
        MultipartFile pictureFile = item.getPicture();
        if(pictureFile != null && pictureFile.getSize()>0){
            String createdFileName = uploadFile(pictureFile.getOriginalFilename(),pictureFile.getBytes());

            item.setPictureUrl(createdFileName);

        }
        MultipartFile previewFile = item.getPreview();
        if(previewFile !=null && previewFile.getSize()>0){
            String createdFileName = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());
            item.setPreviewUrl(createdFileName);
        }
        itemService.edit(item);
        return "redirect:/item/list";
    }

    @GetMapping("/remove")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeForem(Long itemId, Model model) throws Exception{
      Item item = itemService.read(itemId);
      model.addAttribute(item);


    }


    @PostMapping("/remove")
    @PreAuthorize("hasRole('ADMIN')")
    public String remove(Item item,RedirectAttributes rttr)throws Exception{

        itemService.remove(item.getItemId());
        return "redirect:/item/list";





    }




    @PostMapping("/buy")
    public String buy(Long itemId, RedirectAttributes rttr, Authentication authentication) throws Exception{
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Member member = customUser.getMember();

        Long userNo = member.getUserNo();
        member.setCoin(memberService.getCoin(userNo));

        Item item = itemService.read(itemId);

        userItemService.register(member, item);

        log.info("buySuccess");

        String message = messageSource.getMessage("item.purchaseComplete",null, Locale.KOREAN);
        rttr.addFlashAttribute("msg",message);
        return "redirect:/item/success";


    }



    @GetMapping("/success")
    public void success(){
        log.info("success!!@@@@@@");
    }

            private String uploadFile(String originalName, byte[]fileData) throws Exception{
                UUID uid = UUID.randomUUID();

                String createdFileName= uid.toString()+"_"+originalName;


                String uploadPath = shopProperties.getUploadPath();
                File target = new File(uploadPath, createdFileName);

                FileCopyUtils.copy(fileData, target);

                return createdFileName;

            }


            //미리보기 이미지 표시
    @ResponseBody
    @GetMapping("/display")
    public ResponseEntity<byte[]> displayFile(Long itemId) throws Exception{
        InputStream in =null;
        ResponseEntity<byte[]> entity = null;

        String fileName = itemService.getPreview(itemId);

        try{
            //확장자 짜르기
            String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

            log.info("formatName :" +formatName);
            MediaType mType = getMediaType(formatName);

            HttpHeaders headers = new HttpHeaders();

            String uploadPath = shopProperties.getUploadPath();
            in = new FileInputStream(uploadPath+File.separator+fileName);

            if(mType !=null){
                headers.setContentType(mType);
            }

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
            entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }finally {
            in.close();
        }
            return entity;
    }
        ///이미지 형식 확인하기
    private MediaType getMediaType(String formatName) {
        if(formatName != null){
            if(formatName.equals("JPG")){
                return MediaType.IMAGE_JPEG;
            }
            if(formatName.equals("PNG")){
                return MediaType.IMAGE_PNG;
            }
            if (formatName.equals("GIF")){
                return MediaType.IMAGE_GIF;
            }

            }
        return null;
    }


}
