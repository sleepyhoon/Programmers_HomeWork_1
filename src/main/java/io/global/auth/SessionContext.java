package io.global.auth;

import io.domain.member.role.Role;

public class SessionContext {
    private static Session currentSession = null;

    public static Session getCurrentSession() {
        return currentSession;
    }

    public static void signIn(Integer memberId, Role role) {
        currentSession = new Session(memberId, role);
    }

    public static void signOut() {
        currentSession = null;
    }
}