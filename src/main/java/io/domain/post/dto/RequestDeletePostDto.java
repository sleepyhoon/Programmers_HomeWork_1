package io.domain.post.dto;

import io.global.auth.Session;

public class RequestDeletePostDto {
    private final Integer currentMemberId;
    private final Integer removePostId;
    private RequestDeletePostDto(Integer currentMemberId, Integer removePostId) {
        this.currentMemberId = currentMemberId;
        this.removePostId = removePostId;
    }

    public Integer getCurrentMemberId() {
        return currentMemberId;
    }

    public Integer getRemovePostId() {
        return removePostId;
    }

    public static RequestDeletePostDto from(Integer currentMemberId, Integer removePostId) {
        return new RequestDeletePostDto(currentMemberId,removePostId);
    }
}
