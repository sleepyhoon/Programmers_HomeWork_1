package com.domain.service;

import com.domain.dto.post.CreatePostDto;
import com.domain.dto.post.ResponsePostDto;
import com.domain.dto.post.RequestPostDto;
import com.domain.dto.post.UpdatePostDto;
import com.domain.entity.Board;
import com.domain.entity.Post;
import com.domain.repository.BoardRepository;
import com.domain.repository.PostRepository;
import com.global.exception.NoSuchPostException;
import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

    public Integer create(CreatePostDto createPostDto) {
        Post post = Post.from(createPostDto);
        postRepository.save(post);
        Board board = boardRepository.getBoard(createPostDto.getBoardId());
        board.addPost(post);
        boardRepository.save(board);
        return post.getId();
    }

    public ResponsePostDto select(RequestPostDto requestPostDto) {
        Integer postId = requestPostDto.getId();
        if (!postRepository.contains(postId)) {
            throw new NoSuchPostException(postId + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(postId);
        return ResponsePostDto.from(post);
    }

    public List<ResponsePostDto> selectAll() {
        List<Post> all = postRepository.getAll();
        return all.stream()
                .map(ResponsePostDto::from)
                .toList();
    }

    public Integer update(UpdatePostDto updatePostDto) {
        if (!postRepository.contains(updatePostDto.getId())) {
            throw new NoSuchPostException(updatePostDto.getId() + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(updatePostDto.getId());
        post.setTitle(updatePostDto.getTitle());
        post.setContent(updatePostDto.getContent());
        post.setUpdated(LocalDateTime.now());
        postRepository.update(post);
        return post.getId();
    }

    public void delete(RequestPostDto requestPostDto) {
        if (!postRepository.contains(requestPostDto.getId())) {
            throw new NoSuchPostException(requestPostDto.getId() + "번 게시글은 존재하지 않습니다.");
        }
        postRepository.delete(requestPostDto.getId());
    }
}
