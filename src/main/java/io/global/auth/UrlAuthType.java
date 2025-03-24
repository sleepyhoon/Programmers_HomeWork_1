package io.global.auth;

public enum UrlAuthType {
    ANONYMOUS, // 로그인 안해야 함
    HAS_AUTH, // 로그인 해야 함
    PERMIT_ALL, // 상관 없음
    ADMIN // 관리자
    ;

    String value;

    public String getValue() {
        return value;
    }
}
