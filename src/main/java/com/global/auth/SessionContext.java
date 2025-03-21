package com.global.auth;

public class SessionContext {
    private static String currentUser = null;

    // post 에 유저이름 등록할 때 사용한다.
    public static String getCurrentUser() {
        return currentUser == null ? "비회원" : currentUser;
    }

    public static void signIn(String username) {
        currentUser = username;
    }

    public static void signOut() {
        currentUser = null;
    }

    public static boolean currentUserIsNull() {
        return currentUser == null;
    }
}
