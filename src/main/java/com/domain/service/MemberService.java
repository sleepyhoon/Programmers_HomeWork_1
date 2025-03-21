package com.domain.service;

import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.ResponseMemberDetail;
import com.domain.entity.Member;
import com.domain.repository.MemberRepository;
import com.global.auth.SessionContext;
import com.global.exception.DuplicateSignInException;
import com.global.exception.NoSuchMemberException;
import com.global.exception.NotLoggedInException;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Integer signUp(CreateMemberDto createMemberDto) {
        Member member = Member.of(createMemberDto);
        memberRepository.save(member);
        return member.getId();
    }

    public boolean signIn(String username, String password) {
        if(SessionContext.isSignInState()) {
            throw new DuplicateSignInException("이미 로그인 상태입니다.");
        }
        SessionContext.signIn(username);
        return memberRepository.signin(username,password);
    }

    public void signOut() {
        if(!SessionContext.isSignInState()) {
            throw new NotLoggedInException("로그인 상태가 아닙니다.");
        }
        SessionContext.signOut();
    }

    public ResponseMemberDetail detail(String userId) {
        Integer id = Integer.parseInt(userId);
        Member member = memberRepository.get(id).orElseThrow(
                () -> new NoSuchMemberException("해당 id를 가진 멤버가 없습니다.")
        );
        return ResponseMemberDetail.from(member);
    }

    public void edit() {

    }

    public void remove() {

    }
}
