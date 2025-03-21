package com.domain.dto.member;

public class CreateMemberDto {
    private final String username;
    private final String password;
    private final String nickname;
    private final String email;

    private CreateMemberDto(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public static CreateMemberDto of(String username, String password, String nickname, String email) {
        return new CreateMemberDto(username, password, nickname, email);
    }
}
