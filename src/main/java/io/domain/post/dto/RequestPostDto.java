package io.domain.post.dto;

public class RequestPostDto {
    private Integer id;

    private RequestPostDto(String id) {
        this.id = Integer.valueOf(id);
    }

    public Integer getId() {
        return id;
    }

    public static RequestPostDto from(String postId) {
        return new RequestPostDto(postId);
    }
}