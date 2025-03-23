package io.domain.post.service;

import io.domain.post.dto.CreatePostDto;
import io.domain.post.dto.RequestPostDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.post.dto.UpdatePostDto;
import io.domain.board.entity.Board;
import io.domain.post.entity.Post;
import io.domain.board.dao.BoardRepository;
import io.domain.member.dao.MemberRepository;
import io.domain.post.dao.PostRepository;
import io.global.auth.SessionContext;
import io.global.exception.NoSuchMemberException;
import io.global.exception.NoSuchPostException;
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

    // board 객체에 post을 추가하는 것은 컨트롤러에서 구현하는게 좋다고 함. 역할 분리를 구현해보자.
    public Integer create(CreatePostDto createPostDto) {
        Post post = Post.of(createPostDto);
        postRepository.save(post);
        Board board = boardRepository.getBoardById(createPostDto.getBoardId());
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
