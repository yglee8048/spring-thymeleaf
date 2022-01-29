package com.lgcns.icst.springthymeleaf.lec3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class BasicController {

    @GetMapping("/hello-thymeleaf")
    public String textBasic(Model model) {
        model.addAttribute("data", "<b>Hello Thymeleaf!</b>"); // 기본적으로 escape 처리를 해준다!
        return "lec3/hello-thymeleaf";
    }

    @GetMapping("/member")
    public String member(Model model) {
        Member member = new Member("id", "pw", "name", 0);
        model.addAttribute("member", member);
        return "lec3/member";
    }

    @GetMapping("/for-if")
    public String forIf(Model model) {
        List<Member> members = Arrays.asList(
                new Member("id", "pw", "name", 10),
                new Member("id2", "pw2", "name2", 20));

        model.addAttribute("members", members);
        return "lec3/sample-table";
    }

    @GetMapping("/basic-object")
    public String basicObject(HttpSession session) {
        session.setAttribute("memberId", "session-test");
        return "lec3/basic-object";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("name", "my-name");
        model.addAttribute("age", 20);
        model.addAttribute("pathVariable", 2022);
        return "lec3/link";
    }

    @GetMapping("/others")
    public String others(Model model) {
        model.addAttribute("today", LocalDateTime.now());
        return "lec3/others";
    }
}
