package com.domain.service;

import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.ResponseMemberDetail;
import com.domain.dto.member.UpdateMemberDto;
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
        if(!SessionContext.currentUserIsNull()) {
            throw new DuplicateSignInException("이미 로그인 상태입니다.");
        }
        Integer memberId = memberRepository.getIdByUsername(username).orElseThrow(
                () -> new NoSuchMemberException("해당 username을 가진 멤버가 없습니다.")
        );
        if(memberRepository.isCorrectDetail(username,password)){
            SessionContext.signIn(memberId);
            return true;
        } else {
            return false;
        }
    }

    public void signOut() {
        if(SessionContext.currentUserIsNull()) {
            throw new NotLoggedInException("로그인 상태가 아닙니다.");
        }
        SessionContext.signOut();
    }

    public ResponseMemberDetail detail(String userId) {
        Integer id = Integer.parseInt(userId);
        Member member = memberRepository.getById(id).orElseThrow(
                () -> new NoSuchMemberException("해당 id를 가진 멤버가 없습니다.")
        );
        return ResponseMemberDetail.from(member);
    }

    public void edit(UpdateMemberDto memberDto) {
        Member member = memberRepository.getById(memberDto.id()).orElseThrow(
                () -> new NoSuchMemberException("해당 id를 가진 멤버가 없습니다.")
        );
        member.update(memberDto);
        memberRepository.update(member);
    }

    public void remove(String userId) {
        Integer id = Integer.parseInt(userId);
        if(!memberRepository.isExistId(id)) {
            throw new NoSuchMemberException("해당 id를 가진 멤버가 없습니다.");
        }
        memberRepository.remove(id);
    }
}
