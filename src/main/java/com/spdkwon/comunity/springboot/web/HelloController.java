package com.spdkwon.comunity.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 반환하는 컨트롤러
public class HelloController {

    @GetMapping("/hello") // Get의 요청을 받는 API 제작
    public String hello(){
        return "hello";
    }
}
