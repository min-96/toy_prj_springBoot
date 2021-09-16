package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hdcd.domain.Board;
import org.hdcd.domain.CustomUser;
import org.hdcd.domain.Member;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.dto.PaginationDTO;
import org.hdcd.service.BoardService;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void registerForm(Board board, Model model, Authentication authentication)throws Exception{
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Member member = customUser.getMember();

        board.setWriter(member.getUserId());

        model.addAttribute(board);
    }
    @PostMapping("/register")
    @PreAuthorize("hasRole('MEMBER')")
    public String register(Board board,RedirectAttributes rttr) throws Exception{

        boardService.register(board);


        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(@ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model) throws Exception{
        Page<Board> page = boardService.list(pageRequestVO);
        model.addAttribute("pgntn" , new PaginationDTO<Board>(page));


        List<CodeLabelValue> searchTypeCodeValueList=new ArrayList<>();


        searchTypeCodeValueList.add(new CodeLabelValue("n","---"));
        searchTypeCodeValueList.add(new CodeLabelValue("t", "Title"));
        searchTypeCodeValueList.add(new CodeLabelValue("c", "Content"));
        searchTypeCodeValueList.add(new CodeLabelValue("w", "Writer"));
        searchTypeCodeValueList.add(new CodeLabelValue("tc", "Title OR Content"));
        searchTypeCodeValueList.add(new CodeLabelValue("twc", "Title OR Content OR Writer"));

        model.addAttribute("search",searchTypeCodeValueList);

      //  model.addAttribute("list",boardService.list());

    }

    @GetMapping("/read")
    public void read(Long boardNo,@ModelAttribute("pgrq")PageRequestVO pageRequestVO, Model model) throws Exception{
      model.addAttribute(boardService.read(boardNo));
    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('MEMBER')")
    public void edit(Long boardNo,@ModelAttribute("pgrq")PageRequestVO pageRequestVO, Model model)throws Exception{
        model.addAttribute(boardService.read(boardNo));
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('MEMBER')and principal.username ==#board.writer")
    public String edit(Board board,PageRequestVO pageRequestVO,RedirectAttributes rttr)throws Exception{
        boardService.edit(board);

        rttr.addAttribute("page",pageRequestVO.getPage());
        rttr.addAttribute("sizePerPage",pageRequestVO.getSizePerPage());


        rttr.addAttribute("searchType" ,pageRequestVO.getSearchType());
        rttr.addAttribute("keyword",pageRequestVO.getKeyword());

        return "redirect:/board/list";
    }
    @PostMapping("/remove")
    @PreAuthorize("(hasRole('MEMBER')and principal.username ==#writer)or hasRole('ADMIN')")
    public String remove(Long boardNo,PageRequestVO pageRequestVO, RedirectAttributes rttr,String writer) throws Exception{
        boardService.remove(boardNo);

        rttr.addAttribute("page",pageRequestVO.getPage());
        rttr.addAttribute("sizePerPage",pageRequestVO.getSizePerPage());
        rttr.addAttribute("searchType" ,pageRequestVO.getSearchType());
        rttr.addAttribute("keyword",pageRequestVO.getKeyword());
        return "redirect:/board/list";
    }
}
