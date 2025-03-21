package com.domain.controller;

import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.ResponseMemberDetail;
import com.domain.service.MemberService;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public Integer signUp(CreateMemberDto createMemberDto) {
        return memberService.signUp(createMemberDto);
    }

    public boolean signIn(String username, String password) {
        return memberService.signIn(username,password);
    }

    public void signOut() {
        memberService.signOut();
    }

    public ResponseMemberDetail detail(String userId) {
        return memberService.detail(userId);
    }

    public void edit() {

    }

    public void remove() {

    }
}
