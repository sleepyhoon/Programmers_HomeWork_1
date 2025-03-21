package com.domain.repository;

import com.domain.entity.Member;
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

    public Optional<Member> get(Integer userId) {
        return Optional.of(memberHashMap.get(userId));
    }

    public boolean signin(String username, String password) {
        Collection<Member> values = memberHashMap.values();
        for (Member member : values) {
            if(member.isUserInputCorrect(username,password)) {
                return true;
            }
        }
        return false;
    }

    public void update() {

    }

    public void remove() {

    }
}
