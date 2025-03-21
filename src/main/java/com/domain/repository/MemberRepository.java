package com.domain.repository;

import com.domain.entity.Member;
import java.util.HashMap;
import java.util.Optional;

public class MemberRepository {
    private final HashMap<Integer, Member> memberHashMap = new HashMap<>();
    private Integer mapIndex = 1;

    public void save(Member member) {
        member.setId(mapIndex);
        memberHashMap.put(mapIndex++,member);
    }

    public Optional<Member> get(Integer userId) {
        return Optional.of(memberHashMap.get(userId));
    }

    public void update() {

    }

    public void remove() {

    }
}
