package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hdcd.domain.Board;
import org.hdcd.domain.CustomUser;
import org.hdcd.domain.Member;
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
    public void list(Model model) throws Exception{


        model.addAttribute("list",boardService.list());

    }

    @GetMapping("/read")
    public void read(Long boardNo,Model model) throws Exception{
      model.addAttribute(boardService.read(boardNo));
    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('MEMBER')")
    public void edit(Long boardNo,Model model)throws Exception{
        model.addAttribute(boardService.read(boardNo));
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('MEMBER')and principal.username ==#board.writer")
    public String edit(Board board,RedirectAttributes rttr)throws Exception{
        boardService.edit(board);

        return "redirect:/board/list";
    }
    @PostMapping("/remove")
    @PreAuthorize("(hasRole('MEMBER')and principal.username ==#writer)or hasRole('ADMIN')")
    public String remove(Long boardNo, RedirectAttributes rttr,String writer) throws Exception{
        boardService.remove(boardNo);

        return "redirect:/board/list";
    }
}
