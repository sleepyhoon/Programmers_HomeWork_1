package com.domain.controller;

import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.ResponseMemberDetail;
import com.domain.service.MemberService;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public Integer signup(CreateMemberDto createMemberDto) {
        return memberService.signup(createMemberDto);
    }

    public void signin() {

    }

    public void signout() {

    }

    public ResponseMemberDetail detail(String userId) {
        return memberService.detail(userId);
    }

    public void edit() {

    }

    public void remove() {

    }
}
