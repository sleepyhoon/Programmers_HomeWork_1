package io.domain.member.role;

public enum Role {
    ADMIN("ADMIN"),
    MEMBER("MEMBER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
