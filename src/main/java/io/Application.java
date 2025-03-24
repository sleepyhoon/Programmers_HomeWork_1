package io;

import io.domain.board.controller.BoardController;
import io.domain.member.controller.MemberController;
import io.domain.post.controller.PostController;
import io.domain.member.dto.CreateMemberDto;
import io.domain.member.dto.UpdateMemberDto;
import io.domain.view.InputView;
import io.domain.view.OutputView;
import io.global.auth.Session;
import io.global.util.UserRequest;

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

    // 각각의 구현부를 컨트롤러에 위임하는게 더 좋아보임. 인터페이스로 묶으면 다형성 활용 가능.
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
                Integer parameter;
                if (controllerCode.equals("posts")) {
                    switch (target) {
                        case "add" -> {
                            if (userRequest.hasParam("boardId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("boardId", Integer.class);
                            Session session = userRequest.getSession();
                            if (session == null || session.getCurrentMemberId() == null) {
                                OutputView.showLoginRequiredMessage();
                                break;
                            }
                            OutputView.showCreateResult(postController.create(parameter, session));
                        }
                        case "edit" -> {
                            if (userRequest.hasParam("postId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("postId", Integer.class);
                            Session session = userRequest.getSession();
                            if (session == null || session.getCurrentMemberId() == null) {
                                OutputView.showLoginRequiredMessage();
                                break;
                            }
                            OutputView.startUpdate(parameter);
                            OutputView.showUpdateResult(postController.update(parameter, session));
                        }
                        case "remove" -> {
                            if (userRequest.hasParam("postId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("postId", Integer.class);

                            Session session = userRequest.getSession();
                            if (session == null || session.getCurrentMemberId() == null) {
                                OutputView.showLoginRequiredMessage();
                                break;
                            }

                            OutputView.showDeleteResult(postController.delete(session, parameter));
                        }
                        case "view" -> {
                            if (userRequest.hasParam("postId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("postId", Integer.class);
                            OutputView.showPost(postController.select(parameter));
                        }
                        default -> OutputView.showInvalidCommand();
                    }
                } else if (controllerCode.equals("boards")) {
                    switch (target) {
                        case "view" -> {
                            if (userRequest.hasParam("boardName")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            String boardName = userRequest.getValue("boardName", String.class);
                            OutputView.showAllPosts(
                                    boardController.selectAllPosts(userRequest.getSession(), boardName));
                        }
                        case "add" -> {
                            OutputView.showCreateResult(boardController.create(userRequest.getSession()));
                        }
                        case "edit" -> {
                            if (userRequest.hasParam("boardId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("boardId", Integer.class);
                            boardController.update(parameter);
                            OutputView.showUpdateResult(parameter);
                        }
                        case "remove" -> {
                            if (userRequest.hasParam("boardId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("boardId", Integer.class);
                            boardController.delete(parameter);
                            OutputView.showDeleteResult(parameter);
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
                            if (memberController.signIn(userRequest.getSession(), username, userPassword)) {
                                OutputView.showSignInResult();
                            } else {
                                OutputView.showMemberNotFound();
                            }
                        }
                        case "signout" -> {
                            memberController.signOut(userRequest.getSession());
                            OutputView.showSignOutResult();
                        }
                        case "detail" -> {
                            if (userRequest.hasParam("accountId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("accountId", Integer.class);
                            OutputView.showMemberDetail(memberController.detail(parameter));
                        }
                        case "edit" -> {
                            if (userRequest.hasParam("accountId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("accountId", Integer.class);
                            String userPassword = InputView.getUserPassword();
                            String userEmail = InputView.getUserEmail();
                            memberController.edit(UpdateMemberDto.of(parameter, userPassword, userEmail));
                            OutputView.showMemberUpdateResult();
                        }
                        case "remove" -> {
                            if (userRequest.hasParam("accountId")) {
                                System.out.println("[400] 잘못된 요청입니다.");
                            }
                            parameter = userRequest.getValue("accountId", Integer.class);
                            memberController.remove(parameter);
                            OutputView.showMemberDeleteResult();
                        }
                        default -> OutputView.showInvalidCommand();
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("[404] 찾으시는 것이 없습니다.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("올바른 입력을 해주세요!");
            } catch (NumberFormatException e) {
                System.out.println("입력된 숫자 형식을 확인해주세요!");
            } catch (RuntimeException e) {
                System.out.println("[400] 잘못된 요청입니다.");
                e.getStackTrace();
            }
        }
    }
}
