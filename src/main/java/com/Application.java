package com;

import com.domain.controller.BoardController;
import com.domain.controller.MemberController;
import com.domain.controller.PostController;
import com.domain.view.InputView;
import com.domain.view.OutputView;
import com.global.util.URLParser;

public class Application {
    private final PostController postController;
    private final BoardController boardController;
    private final MemberController memberController;

    public Application(PostController postController, BoardController boardController,
                       MemberController memberController) {
        this.postController = postController;
        this.boardController = boardController;
        this.memberController = memberController;
    }

    public void play() {
        while (true) {
            try {
                String userInput = InputView.getUserCommand();
                String[] splitInput = URLParser.parsing(userInput);
                String target = splitInput[0];
                String action = splitInput[1];
                String parameter = "";
                if (splitInput.length == 3) {
                    parameter = splitInput[2];
                }
                if (target.equals("posts")) {
                    switch (action) {
                        case "add" -> OutputView.showCreateResult(postController.create(parameter));
                        case "edit" -> {
                            OutputView.startUpdate(parameter);
                            OutputView.showUpdateResult(postController.update(parameter));
                        }
                        case "remove" -> OutputView.showDeleteResult(postController.delete(parameter));
                        case "view" -> OutputView.showPost(postController.select(parameter));
                        default -> OutputView.showInvalidCommand();
                    }
                } else if (target.equals("boards")) {
                    switch (action) {
                        case "view" -> OutputView.showAllPosts(boardController.selectAllPosts(parameter));
                        case "add" -> OutputView.showCreateResult(boardController.create());
                        case "edit" -> OutputView.showUpdateResult(boardController.update(parameter));
                        case "remove" -> OutputView.showDeleteResult(boardController.delete(parameter));
                        default -> OutputView.showInvalidCommand();
                    }
                } else if (target.equals("accounts")) {
                    switch (action) {
                        case "signup" -> memberController.signup();
                        case "signin" -> memberController.signin();
                        case "signout" -> memberController.signout();
                        case "detail" -> memberController.detail();
                        case "edit" -> memberController.edit();
                        case "remove" -> memberController.remove();
                        default -> OutputView.showInvalidCommand();
                    }
                } else if (target.equals("exit")) {
                    break;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }
    }
}
