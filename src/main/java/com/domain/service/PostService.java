package com.domain.service;

import com.domain.dto.post.CreatePostDto;
import com.domain.dto.post.RequestPostDto;
import com.domain.dto.post.ResponsePostDto;
import com.domain.dto.post.UpdatePostDto;
import com.domain.entity.Board;
import com.domain.entity.Post;
import com.domain.repository.BoardRepository;
import com.domain.repository.MemberRepository;
import com.domain.repository.PostRepository;
import com.global.auth.SessionContext;
import com.global.exception.NoSuchMemberException;
import com.global.exception.NoSuchPostException;
import java.time.LocalDateTime;

public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository,
                       MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public Integer create(CreatePostDto createPostDto) {
        Integer currentUserId = SessionContext.getCurrentMemberId();
        Post post = Post.of(createPostDto, currentUserId);
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
        if(SessionContext.currentUserIsNull()) {
            return ResponsePostDto.of(post,null);
        }
        String username = memberRepository.getUsernameById(post.getMemberId()).orElseThrow(
                () -> new NoSuchMemberException(post.getMemberId() + "번 유저가 없습니다.")
        );
        return ResponsePostDto.of(post,username);
    }

//    public List<ResponsePostDto> selectAll() {
//        List<Post> all = postRepository.getAll();
//        return all.stream()
//                .map(ResponsePostDto::from)
//                .toList();
//    }

    public Integer update(UpdatePostDto updatePostDto) {
        if (!postRepository.contains(updatePostDto.getId())) {
            throw new NoSuchPostException(updatePostDto.getId() + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(updatePostDto.getId());
        // 수정하고 싶다.
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
