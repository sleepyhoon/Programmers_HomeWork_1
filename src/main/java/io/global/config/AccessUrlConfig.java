package io.global.config;

public class AccessUrlConfig {
    // 로그인을 하지 않아야만 접근 가능한 URL
    public static String[] anonymousUrls = {
            "/accounts/signup",
            "/accounts/signin"
    };
    // 관리자만 접근 가능한 URL
    public static String[] hasAdminAuthUrls = {
            "/boards/add",
            "/boards/edit",
            "/boards/remove"
    };
    // 로그인을 하여야만 접근 가능한 URL 목록
    public static String[] needSignInUrls = {
            "/posts/add",
            "/posts/edit",
            "/posts/remove",
            "/accounts/edit",
            "/accounts/signout",
            "/accounts/remove"
    };
}
