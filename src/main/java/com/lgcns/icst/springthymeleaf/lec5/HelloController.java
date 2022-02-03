package com.lgcns.icst.springthymeleaf.lec5;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/response-entity")
    public ResponseEntity<String> helloResponseEntity() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/annotation")
    public String helloAnnotation() {
        return "hello";
    }

    @GetMapping("/servlet-api")
    public void servletApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // read request
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        Sample sample = objectMapper.readValue(messageBody, Sample.class);

        // write response
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = response.getWriter();
        writer.print(objectMapper.writeValueAsString(sample));
    }

    private static class Sample {
        String name;
    }
}
