package io.domain.member.controller;

import io.domain.Controller;
import io.domain.member.dto.CreateMemberDto;
import io.domain.member.dto.ResponseMemberDetail;
import io.domain.member.dto.UpdateMemberDto;
import io.domain.member.service.MemberService;
import io.domain.view.InputView;
import io.domain.view.OutputView;
import io.global.auth.Session;
import io.global.auth.SessionContext;
import io.global.exception.DuplicateSignInException;
import io.global.util.UserRequest;

public class MemberController implements Controller {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void requestHandle(UserRequest userRequest) {
        switch (userRequest.getTarget()) {
            case "signup" -> {
                String username = InputView.getUsername();
                String password = InputView.getUserPassword();
                String nickname = InputView.getUserNickName();
                String email = InputView.getUserEmail();
                OutputView.showSignUpResult(signUp(CreateMemberDto.of(username, password, nickname, email)));
            }
            case "signin" -> {
                String username = InputView.getUsername();
                String userPassword = InputView.getUserPassword();
                if (signIn(username, userPassword)) {
                    OutputView.showSignInResult();
                } else {
                    OutputView.showMemberNotFound();
                }
            }
            case "signout" -> {
                signOut();
                OutputView.showSignOutResult();
            }
            case "detail" -> {
                if (!userRequest.hasParam("accountId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer accountId = userRequest.getValue("accountId", Integer.class);
                OutputView.showMemberDetail(detail(accountId));
            }
            case "edit" -> {
                if (!userRequest.hasParam("accountId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer accountId = userRequest.getValue("accountId", Integer.class);
                String userPassword = InputView.getUserPassword();
                String userEmail = InputView.getUserEmail();
                edit(UpdateMemberDto.of(accountId, userPassword, userEmail));
                OutputView.showMemberUpdateResult();
            }
            case "remove" -> {
                if (!userRequest.hasParam("accountId")) {
                    System.out.println("[400] 잘못된 요청입니다.");
                    return;
                }
                Integer accountId = userRequest.getValue("accountId", Integer.class);
                remove(accountId);
                OutputView.showMemberDeleteResult();
            }
            default -> OutputView.showInvalidCommand();
        }
    }

    private Integer signUp(CreateMemberDto createMemberDto) {
        return memberService.signUp(createMemberDto);
    }

    private boolean signIn(String username, String password) {
        return memberService.signIn(username, password);
    }

    private void signOut() {
        memberService.signOut();
    }

    private ResponseMemberDetail detail(Integer userId) {
        return memberService.detail(userId);
    }

    private void edit(UpdateMemberDto memberDto) {
        memberService.edit(memberDto);
    }

    private void remove(Integer userId) {
        memberService.remove(userId);
    }
}
