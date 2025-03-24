package io.domain.member.entity;

import io.domain.member.dto.CreateMemberDto;
import io.domain.member.dto.UpdateMemberDto;
import io.domain.member.role.Role;
import io.domain.post.entity.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;
    private List<Post> posts;

    private LocalDateTime created;
    private Member(String username, String password, String nickname, String email, Role role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.created = LocalDateTime.now();
        this.role = role;
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isUserInputCorrect(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void update(UpdateMemberDto memberDto) {
        this.password = memberDto.password();
        this.email = memberDto.email();
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public static Member of(CreateMemberDto dto) {
        return new Member(dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getEmail(), Role.MEMBER);
    }

    public static Member ofAdmin() {
        return new Member("admin", "admin1234", "admin", "example@gmail.com", Role.ADMIN);
    }
}
