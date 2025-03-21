package com.domain.entity;

import com.domain.dto.member.CreateMemberDto;
import java.time.LocalDateTime;

public class Member {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private LocalDateTime created;
    private boolean isLoggedIn;

    private Member(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.created = LocalDateTime.now();
        this.isLoggedIn = false;
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

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Member of(CreateMemberDto dto) {
        return new Member(dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getEmail());
    }
}
