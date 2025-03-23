package io.domain.post.dto;

public class RequestSelectPostDto {
    private final Integer id;

    private RequestSelectPostDto(String id) {
        this.id = Integer.valueOf(id);
    }

    public Integer getId() {
        return id;
    }

    public static RequestSelectPostDto from(String postId) {
        return new RequestSelectPostDto(postId);
    }
}
