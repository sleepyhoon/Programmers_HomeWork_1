package com.domain.service;

import com.domain.dto.CreatePostDto;
import com.domain.dto.ResponsePostDto;
import com.domain.dto.RequestPostDto;
import com.domain.dto.UpdatePostDto;
import com.domain.entity.Post;
import com.domain.repository.PostRepository;
import com.global.exception.NoSuchPostException;
import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponsePostDto select(RequestPostDto requestPostDto) {
        Integer postNumber = requestPostDto.getId();
        if(!postRepository.contains(postNumber)) {
            throw new NoSuchPostException(postNumber + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(postNumber);
        return ResponsePostDto.from(post);
    }

    public List<ResponsePostDto> selectAll() {
        List<Post> all = postRepository.getAll();
        return all.stream()
                .map(ResponsePostDto::from)
                .toList();
    }

    public Integer create(CreatePostDto createPostDto) {
        Post post = Post.from(createPostDto);
        postRepository.save(post);
        return post.getId();
    }

    public Integer update(UpdatePostDto updatePostDto) {
        Post post = Post.from(updatePostDto);
        if(!postRepository.contains(updatePostDto.getId())) {
            throw new NoSuchPostException(updatePostDto.getId() + "번 게시글은 존재하지 않습니다.");
        }
        postRepository.update(post);
        return post.getId();
    }

    public void delete(RequestPostDto requestPostDto) {
        if(!postRepository.contains(requestPostDto.getId())) {
            throw new NoSuchPostException(requestPostDto.getId() + "번 게시글은 존재하지 않습니다.");
        }
        postRepository.delete(requestPostDto.getId());
    }
}
