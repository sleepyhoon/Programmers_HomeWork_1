package io.domain.post.controller;

import io.domain.Controller;
import io.domain.board.service.BoardService;
import io.domain.member.service.MemberService;
import io.domain.post.dto.CreatePostDto;
import io.domain.post.dto.RequestDeletePostDto;
import io.domain.post.dto.RequestSelectPostDto;
import io.domain.post.dto.RequestUpdatePostDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.post.dto.UpdatePostDto;
import io.domain.post.entity.Post;
import io.domain.post.service.PostService;
import io.domain.view.InputView;
import io.domain.view.OutputView;
import io.global.auth.Session;
import io.global.util.UserRequest;

public class PostController implements Controller {
    private final PostService postService;
    private final BoardService boardService;
    private final MemberService memberService;

    public PostController(PostService postService, BoardService boardService, MemberService memberService) {
        this.postService = postService;
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @Override
    public void requestHandle(UserRequest userRequest) {

        switch (userRequest.getTarget()) {
            case "add" -> {
                if (!userRequest.hasParam("boardId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                Integer boardId = userRequest.getValue("boardId", Integer.class);
                Session session = userRequest.getSession();
                if (session == null || session.getCurrentMemberId() == null) {
                    OutputView.showLoginRequiredMessage();
                    return;
                }
                OutputView.showCreateResult(create(boardId, session));
            }
            case "edit" -> {
                if (!userRequest.hasParam("postId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                }
                Integer postId = userRequest.getValue("postId", Integer.class);
                Session session = userRequest.getSession();
                if (session == null || session.getCurrentMemberId() == null) {
                    OutputView.showLoginRequiredMessage();
                    return;
                }
                OutputView.startUpdate(postId);
                OutputView.showUpdateResult(update(postId, session.getCurrentMemberId()));
            }
            case "remove" -> {
                if (!userRequest.hasParam("postId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer postId = userRequest.getValue("postId", Integer.class);

                Session session = userRequest.getSession();
                if (session == null || session.getCurrentMemberId() == null) {
                    OutputView.showLoginRequiredMessage();
                    return;
                }

                OutputView.showDeleteResult(delete(session.getCurrentMemberId(), postId));
            }
            case "view" -> {
                if (!userRequest.hasParam("postId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer postId = userRequest.getValue("postId", Integer.class);
                OutputView.showPost(select(postId));
            }
            default -> OutputView.showInvalidCommand();
        }
    }

    private Integer create(Integer boardId, Session session) {
        Integer authorId = session.getCurrentMemberId();
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        // 사실 이렇게 하게 되면 존재하지 않는 boardId로 만들어 버리면 post가 만들어져서 저장되는 문제가 있다. 이거 해결할려면 트랜잭션을 적용해야 되는데..
        // 순수 자바로는 힘들어 보인다.
        Post newPost = postService.create(CreatePostDto.of(boardId, authorId, userPostTitle, userPostContent));
        boardService.addPostToBoard(newPost);
        memberService.addPostToMember(newPost);
        return newPost.getId();
    }

    private ResponsePostDto select(Integer userSelectId) {
        return postService.select(RequestSelectPostDto.from(userSelectId));
    }

    private Integer update(Integer userUpdatePostId, Integer authorId) {
        String userPostTitle = InputView.getUserTitle();
        String userPostContent = InputView.getUserContent();
        return postService.update(UpdatePostDto.of(userUpdatePostId, authorId, userPostTitle, userPostContent));
    }

    private Integer delete(Integer currentMemberId, Integer removePostId) {
        return postService.delete(RequestDeletePostDto.from(currentMemberId, removePostId));
    }

}
