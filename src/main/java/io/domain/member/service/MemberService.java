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
        Member findMember = memberRepository.getMemberByUsername(username).orElseThrow(
                () -> new NoSuchMemberException("해당 username을 가진 멤버가 없습니다.")
        );
        if(findMember.isUserInputCorrect(username,password)){
            SessionContext.signIn(findMember.getId(),findMember.getRole());
            return true;
        } else {
            return false;
        }
    }

    public void signOut() {
        SessionContext.signOut();
    }

    public ResponseMemberDetail detail(Integer userId) {
        Member member = memberRepository.getById(userId).orElseThrow(
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

    public void remove(Integer userId) {
        if(!memberRepository.isExistId(userId)) {
            throw new NoSuchMemberException("해당 id를 가진 멤버가 없습니다.");
        }
        memberRepository.remove(userId);
    }

    public void adminSignUp() {
        Member admin = Member.ofAdmin();
        memberRepository.save(admin);
    }
}
