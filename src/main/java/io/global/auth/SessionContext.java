package io.global.auth;

public class SessionContext {
    private static Session currentSession = null;
    private static final Integer NOT_FOUND_ID = -1;

    public static Integer getCurrentMemberId() {
        return (currentSession == null || currentSession.getCurrentMemberId() == null)
                ? NOT_FOUND_ID
                : currentSession.getCurrentMemberId();
    }

    public static void signIn(Integer memberId) {
        currentSession = new Session(memberId);
    }

    public static void signOut() {
        currentSession = null;
    }

    public static boolean currentUserIsNull() {
        return currentSession == null || currentSession.getCurrentMemberId() == null;
    }
}