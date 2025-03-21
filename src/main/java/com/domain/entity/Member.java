package com.domain.entity;

import com.domain.dto.member.CreateMemberDto;
import com.domain.dto.member.UpdateMemberDto;
import java.time.LocalDateTime;

public class Member {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private LocalDateTime created;

    private Member(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.created = LocalDateTime.now();
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

    private void setPassword(String password) {
        this.password = password;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public boolean isUserInputCorrect(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void update(UpdateMemberDto memberDto) {
        this.password = memberDto.password();
        this.email = memberDto.email();
    }

    public static Member of(CreateMemberDto dto) {
        return new Member(dto.getUsername(), dto.getPassword(), dto.getNickname(), dto.getEmail());
    }
}
