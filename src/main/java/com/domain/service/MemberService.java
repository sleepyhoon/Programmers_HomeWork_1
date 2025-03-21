package com.domain.service;

import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.ResponseMemberDetail;
import com.domain.entity.Member;
import com.domain.repository.MemberRepository;
import com.global.exception.NoSuchMemberException;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Integer signup(CreateMemberDto createMemberDto) {
        Member member = Member.of(createMemberDto);
        memberRepository.save(member);
        return member.getId();
    }

    public void signin() {

    }

    public void signout() {

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
