package io.domain.post.dto;

public class RequestSelectPostDto {
    private final Integer id;

    private RequestSelectPostDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static RequestSelectPostDto from(Integer postId) {
        return new RequestSelectPostDto(postId);
    }
}
