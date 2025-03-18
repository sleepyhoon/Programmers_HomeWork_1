package com.domain.config;

import com.domain.controller.PostController;
import com.domain.repository.PostRepository;
import com.domain.service.PostService;
import com.domain.view.InputView;

public abstract class AppConfig {
    private static final PostRepository postRepository = new PostRepository();
    private static final PostService postService = new PostService(postRepository);
    private static final PostController postController = new PostController(postService);

    public static PostController getBoardController() {
        return postController;
    }
}
