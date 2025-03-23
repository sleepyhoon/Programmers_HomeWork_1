package io.domain.member.service;

import io.domain.member.dto.CreateMemberDto;
import io.domain.member.dto.ResponseMemberDetail;
import io.domain.member.dto.UpdateMemberDto;
import io.domain.member.entity.Member;
import io.domain.member.dao.MemberRepository;
import io.global.auth.SessionContext;
import io.global.exception.NoSuchMemberException;

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
