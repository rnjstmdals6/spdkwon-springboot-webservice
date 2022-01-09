package com.spdkwon.comunity.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반활할 때 아퓨의 경로와 뒤의 파일 확장자는 자동으로 지정됨
    {
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
