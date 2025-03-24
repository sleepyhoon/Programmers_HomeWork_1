package io.global.config;

import io.Application;
import io.domain.board.controller.BoardController;
import io.domain.member.controller.MemberController;
import io.domain.post.controller.PostController;
import io.domain.board.dao.BoardRepository;
import io.domain.member.dao.MemberRepository;
import io.domain.post.dao.PostRepository;
import io.domain.board.service.BoardService;
import io.domain.member.service.MemberService;
import io.domain.post.service.PostService;

public abstract class AppConfig {
    private static final PostRepository postRepository = new PostRepository();
    private static final BoardRepository boardRepository = new BoardRepository();
    private static final MemberRepository memberRepository = new MemberRepository();
    private static final PostService postService = new PostService(postRepository, memberRepository);
    private static final BoardService boardService = new BoardService(boardRepository, memberRepository);
    private static final MemberService memberService = new MemberService(memberRepository);
    private static final PostController postController = new PostController(postService, boardService, memberService);
    private static final BoardController boardController = new BoardController(boardService);
    private static final MemberController memberController = new MemberController(memberService);
    private static final Application application = new Application(postController, boardController, memberController);

    public static Application getApplication() {
        return application;
    }

    // 관리자 계정 프로그램 시작하자마자 생성하기
    static {
        memberService.adminSignUp();
    }
}
