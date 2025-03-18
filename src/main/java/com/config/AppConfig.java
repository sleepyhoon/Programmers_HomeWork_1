package com.config;

import com.controller.PostController;
import com.repository.PostRepository;
import com.service.PostService;
import com.view.InputView;

public abstract class AppConfig {
    private static final InputView inputview = new InputView();
    private static final PostRepository postRepository = new PostRepository();
    private static final PostService postService = new PostService(postRepository);
    private static final PostController postController = new PostController(inputview, postService);

    public static PostController getBoardController() {
        return postController;
    }
}
