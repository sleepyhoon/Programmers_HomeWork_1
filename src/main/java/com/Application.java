package com;

import com.domain.controller.PostController;
import com.domain.view.InputView;
import com.domain.view.OutputView;

public class Application {
    private final PostController postController;

    public Application(PostController postController) {
        this.postController = postController;
    }

    public void play() {
        try {
            while (true) {
                String userInput = InputView.getUserCommand();
                switch (userInput) {
                    case "작성" -> OutputView.showCreateResult(postController.create());
                    case "조회" -> OutputView.showPost(postController.select());
                    case "수정" -> {
                        String userUpdateNumber = InputView.getUserUpdateNumber();
                        OutputView.startUpdate(userUpdateNumber);
                        OutputView.showUpdateResult(postController.update(userUpdateNumber));
                    }
                    case "삭제" -> OutputView.showDeleteResult(postController.delete());
                    case "목록" -> OutputView.showAllPosts(postController.selectAll());
                    default -> OutputView.showInvalidCommand();
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
