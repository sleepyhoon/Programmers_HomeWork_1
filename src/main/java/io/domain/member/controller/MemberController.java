package io.domain.member.controller;

import io.domain.member.dto.CreateMemberDto;
import io.domain.member.dto.ResponseMemberDetail;
import io.domain.member.dto.UpdateMemberDto;
import io.domain.member.service.MemberService;
import io.global.auth.Session;
import io.global.auth.SessionContext;
import io.global.exception.DuplicateSignInException;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public Integer signUp(CreateMemberDto createMemberDto) {
        return memberService.signUp(createMemberDto);
    }

    public boolean signIn(Session session, String username, String password) {
        if(session != null) {
            throw new DuplicateSignInException("이미 로그인 상태입니다.");
        }
        return memberService.signIn(username,password);
    }

    public void signOut(Session session) {
        if(session == null) {
            throw new DuplicateSignInException("이미 로그아웃 상태입니다.");
        }
        memberService.signOut();
    }

    public ResponseMemberDetail detail(Integer userId) {
        return memberService.detail(userId);
    }

    public void edit(UpdateMemberDto memberDto) {
        memberService.edit(memberDto);
    }

    public void remove(Integer userId) {
        memberService.remove(userId);
    }
}
