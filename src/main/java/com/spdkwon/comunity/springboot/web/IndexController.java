package com.spdkwon.comunity.springboot.web;

import com.spdkwon.comunity.springboot.config.auth.LoginUser;
import com.spdkwon.comunity.springboot.config.auth.dto.SessionUser;
import com.spdkwon.comunity.springboot.service.PostsService.PostsService;
import com.spdkwon.comunity.springboot.web.dto.PostsResponseDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){ // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반활할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
