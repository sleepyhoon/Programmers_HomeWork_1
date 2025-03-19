package com.domain.config;

import com.Application;
import com.domain.controller.BoardController;
import com.domain.controller.PostController;
import com.domain.repository.BoardRepository;
import com.domain.repository.PostRepository;
import com.domain.service.BoardService;
import com.domain.service.PostService;

public abstract class AppConfig {
    private static final PostRepository postRepository = new PostRepository();
    private static final PostService postService = new PostService(postRepository);
    private static final PostController postController = new PostController(postService);
    private static final BoardRepository boardRepository = new BoardRepository();
    private static final BoardService boardService = new BoardService(boardRepository);
    private static final BoardController boardController = new BoardController(boardService);
    private static final Application application = new Application(postController, boardController);

    public static Application getApplication() {
        return application;
    }
}
