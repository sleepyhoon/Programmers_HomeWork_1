package com.domain.controller;

import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.ResponseMemberDetail;
import com.domain.dto.member.UpdateMemberDto;
import com.domain.service.MemberService;
import com.global.auth.SessionContext;
import com.global.exception.DuplicateSignInException;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public Integer signUp(CreateMemberDto createMemberDto) {
        return memberService.signUp(createMemberDto);
    }

    public boolean signIn(String username, String password) {
        if(!SessionContext.currentUserIsNull()) {
            throw new DuplicateSignInException("이미 로그인 상태입니다.");
        }
        return memberService.signIn(username,password);
    }

    public void signOut() {
        if(SessionContext.currentUserIsNull()) {
            throw new DuplicateSignInException("이미 로그아웃 상태입니다.");
        }
        memberService.signOut();
    }

    public ResponseMemberDetail detail(String userId) {
        return memberService.detail(userId);
    }

    public void edit(UpdateMemberDto memberDto) {
        memberService.edit(memberDto);
    }

    public void remove(String userId) {
        memberService.remove(userId);
    }
}
