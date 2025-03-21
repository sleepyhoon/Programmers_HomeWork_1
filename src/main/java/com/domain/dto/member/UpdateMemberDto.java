package com.domain.dto.member;

public record UpdateMemberDto(
        Integer id,
        String password,
        String email
) {
    public static UpdateMemberDto of(String id, String password, String email) {
        return new UpdateMemberDto(Integer.parseInt(id), password, email);
    }
}
