package io;

import io.domain.Controller;
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
                Controller controller = getController(userRequest.getControllerCode());
                if(controller == null) {
                    System.out.println("[400] 잘못된 요청입니다.");
                } else {
                    controller.requestHandle(userRequest);
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

    private Controller getController(String controllerCode) {
        switch (controllerCode) {
            case "posts" -> {
                return postController;
            }
            case "boards" -> {
                return boardController;
            }
            case "accounts" -> {
                return memberController;
            }
            default -> {
                return null;
            }
        }
    }
}
