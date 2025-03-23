package io.domain.member.dto;

import io.domain.member.entity.Member;
import java.time.LocalDateTime;

public class ResponseMemberDetail {
    private final String username;
    private final String email;
    private final String nickname;
    private final LocalDateTime created;

    private ResponseMemberDetail(String username, String email, String nickname, LocalDateTime created) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.created = created;
    }

    @Override
    public String toString() {
        return "ResponseMemberDetail{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", created=" + created +
                '}';
    }

    public static ResponseMemberDetail from(Member member) {
        return new ResponseMemberDetail(member.getUsername(), member.getEmail(), member.getNickname(), member.getCreated());
    }
}
