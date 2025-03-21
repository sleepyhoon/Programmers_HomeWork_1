package com.global.auth;

public class SessionContext {
    private static Integer currentMemberId = null;
    private static final Integer NOT_FOUND_ID = -1;

    // post 에 유저이름 등록할 때 사용한다.
    public static Integer getCurrentMemberId() {
        return currentMemberId == null ? NOT_FOUND_ID : currentMemberId;
    }

    public static void signIn(Integer memberId) {
        currentMemberId = memberId;
    }

    public static void signOut() {
        currentMemberId = null;
    }

    public static boolean currentUserIsNull() {
        return currentMemberId == null;
    }
}
