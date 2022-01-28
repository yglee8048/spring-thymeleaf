package com.lgcns.icst.springthymeleaf.lec3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/valid")
public class ValidationController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("member", new MemberEntity("id", "pw", "name", 1));
        return "lec4/validation-test";
    }

    @PostMapping("/form")
    public String validTest(@Validated @ModelAttribute MemberEntity memberEntity) {
        return "/lec4/validation-test";
    }

    @GetMapping("/error-test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.sendError(500, "Internal Servlet Error!");
        throw new ServletException("");
    }
}
