package com.spdkwon.comunity.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { // Dao같은 DB Layer 접근자
    
}
