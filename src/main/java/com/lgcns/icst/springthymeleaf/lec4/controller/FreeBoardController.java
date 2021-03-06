package com.lgcns.icst.springthymeleaf.lec4.controller;

import com.lgcns.icst.springthymeleaf.lec4.biz.FreeBoardBiz;
import com.lgcns.icst.springthymeleaf.lec4.constant.SessionKey;
import com.lgcns.icst.springthymeleaf.lec4.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/lec4/free-boards")
public class FreeBoardController {

    private final FreeBoardBiz freeBoardBiz;

    @Autowired
    public FreeBoardController(FreeBoardBiz freeBoardBiz) {
        this.freeBoardBiz = freeBoardBiz;
    }

    @GetMapping
    public String freeBoardListView(Model model) {
        try {
            List<FreeBoardDTO> freeBoards = freeBoardBiz.findAll();
            model.addAttribute("freeBoards", freeBoards);
            return "lec4/freeBoard/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @GetMapping("/write")
    public String freeBoardWriteForm(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                                     @ModelAttribute(name = "freeBoard") FreeBoardDTO freeBoard) {
        if (memberId == null) {
            return "redirect:/lec4/member/login";
        }
        return "lec4/freeBoard/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute FreeBoardDTO freeBoard,
                        @SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId, Model model) {
        if (memberId == null) {
            return "redirect:/lec4/member/login";
        }

        try {
            freeBoardBiz.save(freeBoard.getContent(), memberId);
            return "redirect:/lec4/free-boards";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @GetMapping("/{id}/update")
    public String freeBoardUpdateForm(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                                      @PathVariable Long id, Model model) {
        if (memberId == null) {
            return "redirect:/lec4/member/login";
        }

        try {
            FreeBoardDTO freeBoard = freeBoardBiz.findFreeBoardById(id);
            model.addAttribute("freeBoard", freeBoard);
            return "lec4/freeBoard/update";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                         @PathVariable Long id, @ModelAttribute FreeBoardDTO freeBoard, Model model) {
        if (memberId == null) {
            return "redirect:/lec4/member/login";
        }

        try {
            freeBoardBiz.update(id, freeBoard.getContent());
            return "redirect:/lec4/free-boards";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                         @PathVariable Long id, Model model) {
        if (memberId == null) {
            return "redirect:/lec4/member/login";
        }

        try {
            freeBoardBiz.delete(id);
            return "redirect:/lec4/free-boards";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }
}
