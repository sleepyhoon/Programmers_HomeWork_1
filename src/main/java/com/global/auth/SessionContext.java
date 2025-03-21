package com.global.auth;

public class SessionContext {
    private static String currentUser = null;

    public static String getCurrentUser() {
        return currentUser == null ? "비회원" : currentUser;
    }

    public static void signIn(String username) {
        currentUser = username;
    }

    public static void signOut() {
        currentUser = null;
    }

    public static boolean isSignInState() {
        return currentUser != null;
    }
}
