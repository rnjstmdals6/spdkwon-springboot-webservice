package com.spdkwon.comunity.springboot.service.PostsService;

import com.spdkwon.comunity.springboot.domain.posts.Posts;
import com.spdkwon.comunity.springboot.domain.posts.PostsRepository;
import com.spdkwon.comunity.springboot.web.dto.PostsListResponseDto;
import com.spdkwon.comunity.springboot.web.dto.PostsResponseDto;
import com.spdkwon.comunity.springboot.web.dto.PostsSaveRequestDto;
import com.spdkwon.comunity.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것으로 조회 속도가 개선
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        // .map(posts -> new PostsListResponseDto(posts)) 위와 동일한 코드
        //
    }

}
