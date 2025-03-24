package io.domain.member.dto;

public record UpdateMemberDto(
        Integer id,
        String password,
        String email
) {
    public static UpdateMemberDto of(Integer id, String password, String email) {
        return new UpdateMemberDto(id, password, email);
    }
}
