package com.domain.config;

import com.domain.Application;
import com.domain.controller.PostController;
import com.domain.repository.PostRepository;
import com.domain.service.PostService;

public abstract class AppConfig {
    private static final PostRepository postRepository = new PostRepository();
    private static final PostService postService = new PostService(postRepository);
    private static final PostController postController = new PostController(postService);
    private static final Application application = new Application(postController);

    public static Application getApplication() {
        return application;
    }
}
