package io.domain.member.dao;

import io.domain.member.entity.Member;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class MemberRepository {
    private final HashMap<Integer, Member> memberHashMap = new HashMap<>();
    private Integer mapIndex = 1;

    public void save(Member member) {
        member.setId(mapIndex);
        memberHashMap.put(mapIndex++,member);
    }

    public Optional<Member> getById(Integer userId) {
        return Optional.ofNullable(memberHashMap.get(userId));
    }

    public Optional<Integer> getIdByUsername(String username) {
        Collection<Member> values = memberHashMap.values();
        for (Member member : values) {
            if(member.getUsername().equals(username)) {
                return Optional.of(member.getId());
            }
        }
        return Optional.empty();
    }

    public Optional<String> getUsernameById(Integer memberId) {
        Member member = memberHashMap.get(memberId);
        return Optional.of(member.getUsername());
    }

    public boolean isExistId(Integer userId) {
        return memberHashMap.containsKey(userId);
    }

    public boolean isCorrectDetail(String username, String password) {
        Collection<Member> values = memberHashMap.values();
        for (Member member : values) {
            if(member.isUserInputCorrect(username,password)) {
                return true;
            }
        }
        return false;
    }

    public void update(Member member) {
        memberHashMap.put(member.getId(), member);
    }

    public void remove(Integer userId) {
        memberHashMap.remove(userId);
    }
}
