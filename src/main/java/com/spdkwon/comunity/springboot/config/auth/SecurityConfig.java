package com.spdkwon.comunity.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests() // URL 별 권한 관리설정 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 관리 대상을 지정, permitAll()을 통해 전체 열람권한
                .anyRequest() // 설정된 값들 이외 나머지 URL들을 나타냄
                .authenticated() //authenticated()를 축해 나머지 URL들은 모두 인증된 사용자들에게만 허용
                .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정의 진입점, 로그아웃 성공시 / 주소로 이동
                .and().oauth2Login().userInfoEndpoint() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userService(customOAuth2UserService); // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스 구현체를 등록
    }
}
