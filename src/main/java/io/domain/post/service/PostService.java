package io.domain.post.service;

import io.domain.post.dto.CreatePostDto;
import io.domain.post.dto.RequestDeletePostDto;
import io.domain.post.dto.RequestSelectPostDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.post.dto.UpdatePostDto;
import io.domain.post.entity.Post;
import io.domain.member.dao.MemberRepository;
import io.domain.post.dao.PostRepository;
import io.global.auth.Session;
import io.global.exception.NotFoundMemberException;
import io.global.exception.NotFoundPostException;
import io.global.exception.UnauthorizedAccessException;

public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository,
                       MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Post create(CreatePostDto createPostDto) {
        Post post = Post.of(createPostDto);
        postRepository.save(post);
        return post;
    }

    public ResponsePostDto select(RequestSelectPostDto requestSelectPostDto) {
        Integer postId = requestSelectPostDto.getId();
        if (!postRepository.contains(postId)) {
            throw new NotFoundPostException(postId + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(postId);
        String username = memberRepository.getUsernameById(post.getAuthorId()).orElseThrow(
                () -> new NotFoundMemberException(post.getAuthorId() + "번 유저가 없습니다.")
        );
        return ResponsePostDto.of(post, username);
    }

    public Integer update(UpdatePostDto updatePostDto) {
        if (!postRepository.contains(updatePostDto.getId())) {
            throw new NotFoundPostException(updatePostDto.getId() + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(updatePostDto.getId());
        Session session = updatePostDto.getSession();
        if (session.isNotAdmin() && !post.getAuthorId().equals(session.getCurrentMemberId())) {
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
            throw new NotFoundPostException(currentMemberId + "번 게시글은 존재하지 않습니다.");
        }
        Post post = postRepository.get(currentMemberId);
        if (session.isNotAdmin() && !post.getAuthorId().equals(currentMemberId)) {
            throw new UnauthorizedAccessException("본인이 작성한 게시글이 아닙니다");
        }

        Integer removePostId = requestDeletePostDto.getRemovePostId();
        postRepository.delete(removePostId);
        return removePostId;
    }
}
