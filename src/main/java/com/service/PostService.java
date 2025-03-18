package com.service;

import com.dto.CreatePostDto;
import com.dto.ResponsePostDto;
import com.dto.RequestPostDto;
import com.dto.UpdatePostDto;
import com.entity.Post;
import com.repository.PostRepository;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponsePostDto select(RequestPostDto requestPostDto) {
        Integer postNumber = requestPostDto.getPostNumber();
        Post post = postRepository.get(postNumber);
        return ResponsePostDto.from(post);
    }

    public Integer create(CreatePostDto createPostDto) {
        Post post = Post.from(createPostDto);
        postRepository.save(post);
        return post.getId();
    }

    public Integer update(UpdatePostDto updatePostDto) {
        Post post = Post.from(updatePostDto);
        postRepository.update(post);
        return post.getId();
    }

    public void delete(RequestPostDto requestPostDto) {
        postRepository.delete(requestPostDto.getPostNumber());
    }
}
