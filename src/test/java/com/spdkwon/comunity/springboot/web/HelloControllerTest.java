package com.spdkwon.comunity.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트 진행 시 JUnit에 내장된 실행자가 아닌 SpringRunner라는 스프링 실행자를 사용
@WebMvcTest(controllers = HelloController.class) // 컨트롤러만 사용하고 Web에 집중할 수 있는 테스트 어노테이션
public class HelloControllerTest {

    @Autowired // Spring이 관리하는 Bean을 주입받음
    private MockMvc mvc; // 웹 API를 테스트할 때 사용하고 스프링 MVC 테스트의 시작점

    @Test
    public void hello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform의 결과가 200인지 아닌지 상태 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과검증
    }

    @Test
    public void helloDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
