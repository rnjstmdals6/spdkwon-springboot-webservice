package com.spdkwon.comunity.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복, Entity 클래스에서는 Setter 를 절대 만들지 않음
@NoArgsConstructor // 롬복
@Entity // JPA 테이블과 링크될 클래스 등록
public class Posts {

    @Id // 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블 칼럼선언(사이즈늘리거나 타입변경시 씀)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
