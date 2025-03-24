package io.domain.member.dto;

import io.domain.member.entity.Member;
import io.domain.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;

public class ResponseMemberDetail {
    private final String username;
    private final String email;
    private final String nickname;
    private final List<Post> posts;
    private final LocalDateTime created;

    private ResponseMemberDetail(String username, String email, String nickname, List<Post> posts,
                                 LocalDateTime created) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.posts = posts;
        this.created = created;
    }

    @Override
    public String toString() {
        return "ResponseMemberDetail{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", posts=" + posts +
                ", created=" + created +
                '}';
    }

    public static ResponseMemberDetail from(Member member) {
        return new ResponseMemberDetail(member.getUsername(), member.getEmail(), member.getNickname(),
                member.getPosts(), member.getCreated());
    }
}
