package io.global.auth;

public enum UrlAuthType {
    ANONYMOUS("ANONYMOUS"), // 로그인 안해야 함
    HAS_AUTH("HAS_AUTH"), // 로그인 해야 함
    PERMIT_ALL("PERMIT_ALL"), // 상관 없음
    ADMIN("ADMIN") // 관리자
    ;

    private final String value;

    UrlAuthType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
