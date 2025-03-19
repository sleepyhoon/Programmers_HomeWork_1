package com;

import com.domain.controller.BoardController;
import com.domain.controller.PostController;
import com.domain.view.InputView;
import com.domain.view.OutputView;
import com.global.util.URLParser;

public class Application {
    private final PostController postController;
    private final BoardController boardController;

    public Application(PostController postController, BoardController boardController) {
        this.postController = postController;
        this.boardController = boardController;
    }

    public void play() {
        try {
            while (true) {
                String userInput = InputView.getUserCommand();
                String[] splitInput = URLParser.parsing(userInput);
                String target = splitInput[0];
                String action = splitInput[1];
                String parameter = splitInput[2];
                if (target.equals("posts")) {
                    switch (action) {
                        case "add" -> {
//                            OutputView.showCreateResult(postController.create(parameter));
                        }
                        case "edit" -> {
                            OutputView.startUpdate(parameter);
                            OutputView.showUpdateResult(postController.update(parameter));
                        }
                        case "remove" -> {
                            OutputView.showDeleteResult(postController.delete(parameter));
                        }
                        case "view" -> {
//                            OutputView.showAllPosts(postController.selectAll(parameter));
                        }
                        default -> OutputView.showInvalidCommand();
                    }
                } else if (target.equals("boards")) {
                    switch (action) {
                        case "view" -> boardController.selectAllPosts(parameter);
                        case "add" -> boardController.create();
                        case "edit" -> boardController.update(parameter);
                        case "remove" -> boardController.delete(parameter);
                        default -> OutputView.showInvalidCommand();
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
