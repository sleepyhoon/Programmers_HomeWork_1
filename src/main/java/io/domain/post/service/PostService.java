package io.domain.post.service;

import io.domain.post.dto.CreatePostDto;
import io.domain.post.dto.RequestDeletePostDto;
import io.domain.post.dto.RequestSelectPostDto;
import io.domain.post.dto.RequestUpdatePostDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.post.dto.UpdatePostDto;
import io.domain.board.entity.Board;
import io.domain.post.entity.Post;
import io.domain.board.dao.BoardRepository;
import io.domain.member.dao.MemberRepository;
import io.domain.post.dao.PostRepository;
import io.global.auth.Session;
import io.global.exception.NoSuchMemberException;
import io.global.exception.NoSuchPostException;
import io.global.exception.UnauthorizedAccessException;

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

    // board 객체에 post을 추가하는 것은 컨트롤러에서 구현하는게 좋다고 함. 역할 분리를 구현해보자.
    public Integer create(CreatePostDto createPostDto) {
        Post post = Post.of(createPostDto);
        postRepository.save(post);
        Board board = boardRepository.getBoardById(createPostDto.getBoardId());
        board.addPost(post);
        boardRepository.save(board);
        return post.getId();
    }

    public ResponsePostDto select(RequestSelectPostDto requestSelectPostDto) {
        Integer postId = requestSelectPostDto.getId();
        if (!postRepository.contains(postId)) {
            throw new NoSuchPostException(postId + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(postId);
        String username = memberRepository.getUsernameById(post.getAuthorId()).orElseThrow(
                () -> new NoSuchMemberException(post.getAuthorId() + "번 유저가 없습니다.")
        );
        return ResponsePostDto.of(post,username);
    }

    public Integer update(UpdatePostDto updatePostDto) {
        if (!postRepository.contains(updatePostDto.getId())) {
            throw new NoSuchPostException(updatePostDto.getId() + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(updatePostDto.getId());
        Session session = updatePostDto.getSession();
        if(!session.isAdmin() && !post.getAuthorId().equals(session.getCurrentMemberId())) {
            throw new UnauthorizedAccessException("본인이 작성한 게시글이 아닙니다");
        }
        post.update(updatePostDto);
        postRepository.update(post);
        return post.getId();
    }

    public Integer delete(RequestDeletePostDto requestDeletePostDto) {
        Session session = requestDeletePostDto.getSession();
        Integer currentMemberId = session.getCurrentMemberId();
        if (!postRepository.contains(currentMemberId)) {
            throw new NoSuchPostException(currentMemberId + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(currentMemberId);
        if(!post.getAuthorId().equals(currentMemberId)) {
            throw new UnauthorizedAccessException("본인이 작성한 게시글이 아닙니다");
        }

        postRepository.delete(currentMemberId);
        return currentMemberId;
    }
}
