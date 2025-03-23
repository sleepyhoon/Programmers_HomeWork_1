package com;

import com.domain.controller.BoardController;
import com.domain.controller.MemberController;
import com.domain.controller.PostController;
import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.UpdateMemberDto;
import com.domain.view.InputView;
import com.domain.view.OutputView;
import com.global.util.UserRequest;

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
                if (userInput.equals("/.exit")) {
                    OutputView.showTerminateProgram();
                    return;
                }
                UserRequest userRequest = new UserRequest(userInput);
                if (!userRequest.isValid()) {
                    OutputView.showInvalidCommand();
                    continue;
                }
                String controllerCode = userRequest.getControllerCode();
                String target = userRequest.getTarget();
                String parameter = "";
                if (controllerCode.equals("posts")) {
                    switch (target) {
                        case "add" -> {
                            parameter = userRequest.getValue("boardId", String.class);
                            Integer currentMemberId = userRequest.getCurrentMemberId();
                            OutputView.showCreateResult(postController.create(parameter,currentMemberId));
                        }
                        case "edit" -> {
                            parameter = userRequest.getValue("postId", String.class);
                            OutputView.startUpdate(parameter);
                            OutputView.showUpdateResult(postController.update(parameter));
                        }
                        case "remove" -> {
                            parameter = userRequest.getValue("postId", String.class);
                            OutputView.showDeleteResult(postController.delete(parameter));
                        }
                        case "view" -> {
                            parameter = userRequest.getValue("postId", String.class);
                            OutputView.showPost(postController.select(parameter));
                        }
                        default -> OutputView.showInvalidCommand();
                    }
                } else if (controllerCode.equals("boards")) {
                    switch (target) {
                        case "view" -> {
                            parameter = userRequest.getValue("boardName", String.class);
                            OutputView.showAllPosts(boardController.selectAllPosts(parameter));
                        }
                        case "add" -> {
                            Integer currentMemberId = userRequest.getCurrentMemberId();
                            OutputView.showCreateResult(boardController.create(currentMemberId));
                        }
                        case "edit" -> {
                            parameter = userRequest.getValue("boardId", String.class);
                            OutputView.showUpdateResult(boardController.update(parameter));
                        }
                        case "remove" -> {
                            parameter = userRequest.getValue("boardId", String.class);
                            OutputView.showDeleteResult(boardController.delete(parameter));
                        }
                        default -> OutputView.showInvalidCommand();
                    }
                } else if (controllerCode.equals("accounts")) {
                    switch (target) {
                        case "signup" -> {
                            String username = InputView.getUsername();
                            String password = InputView.getUserPassword();
                            String nickname = InputView.getUserNickName();
                            String email = InputView.getUserEmail();
                            OutputView.showSignUpResult(
                                    memberController.signUp(CreateMemberDto.of(username, password, nickname, email)));
                        }
                        case "signin" -> {
                            String username = InputView.getUsername();
                            String userPassword = InputView.getUserPassword();
                            if (memberController.signIn(username, userPassword)) {
                                OutputView.showSignInResult();
                            } else {
                                OutputView.showMemberNotFound();
                            }
                        }
                        case "signout" -> {
                            memberController.signOut();
                            OutputView.showSignOutResult();
                        }
                        case "detail" -> {
                            parameter = userRequest.getValue("accountId", String.class);
                            OutputView.showMemberDetail(memberController.detail(parameter));
                        }
                        case "edit" -> {
                            parameter = userRequest.getValue("accountId", String.class);
                            String userPassword = InputView.getUserPassword();
                            String userEmail = InputView.getUserEmail();
                            memberController.edit(UpdateMemberDto.of(parameter, userPassword, userEmail));
                            OutputView.showMemberUpdateResult();
                        }
                        case "remove" -> {
                            parameter = userRequest.getValue("accountId", String.class);
                            memberController.remove(parameter);
                            OutputView.showMemberDeleteResult();
                        }
                        default -> OutputView.showInvalidCommand();
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("올바른 입력을 해주세요!");
            } catch (NumberFormatException e) {
                System.out.println("입력된 숫자 형식을 확인해주세요!");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }
    }
}
