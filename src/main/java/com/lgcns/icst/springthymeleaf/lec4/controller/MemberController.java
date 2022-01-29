package com.lgcns.icst.springthymeleaf.lec4.controller;

import com.lgcns.icst.springthymeleaf.lec4.biz.MemberBiz;
import com.lgcns.icst.springthymeleaf.lec4.constant.SessionKey;
import com.lgcns.icst.springthymeleaf.lec4.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/lec4/member")
public class MemberController {

    private final MemberBiz memberBiz;

    @Autowired
    public MemberController(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute(name = "member") MemberDTO member) {
        return "lec4/member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO member, HttpSession session, Model model) {
        try {
            member = memberBiz.login(member.getMemberId(), member.getMemberPw());
            session.setAttribute(SessionKey.MEMBER_NAME, member.getMemberName());
            session.setAttribute(SessionKey.MEMBER_ID, member.getMemberId());
            return "redirect:/lec4/free-boards";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @GetMapping("/sign-up")
    public String signUpForm(@ModelAttribute(name = "member") MemberDTO member) {
        return "lec4/member/signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute MemberDTO member, Model model) {
        try {
            memberBiz.signUp(member);
            return "redirect:/lec4/member/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @GetMapping("/update")
    public String updateForm(Model model, @SessionAttribute(name = SessionKey.MEMBER_ID) String memberId) {
        try {
            MemberDTO member = memberBiz.findById(memberId);
            model.addAttribute("member", member);
            return "lec4/member/update";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @PostMapping("/update")
    public String update(@SessionAttribute(name = SessionKey.MEMBER_ID) String memberId,
                         @ModelAttribute MemberDTO member, HttpSession session, Model model) {
        try {
            memberBiz.update(memberId, member.getMemberPw(), member.getMemberName());
            session.setAttribute(SessionKey.MEMBER_NAME, member.getMemberName());
            return "redirect:/lec4/free-boards";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec4/errorPage";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/lec4/member/login";
    }
}
