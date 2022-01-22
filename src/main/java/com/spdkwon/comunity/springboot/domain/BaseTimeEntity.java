package com.spdkwon.comunity.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 이 클래스를 상속할 경우 createdDate, modifiedDate 또한 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 기능을 포함
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 가 생성되어 저장될 때 시간이 자동으로 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // Entity 가 변경되어 저장될 때 시간이 자동으로 저장
    private LocalDateTime modifiedDate;
}
