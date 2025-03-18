package com.domain.dto.post;

public class RequestPostDto {
    private Integer id;

    private RequestPostDto(String id) {
        validate(id);
        this.id = Integer.valueOf(id.substring(0, id.length() - 1));
    }

    public Integer getId() {
        return id;
    }

    public static RequestPostDto from(String postNumber) {
        return new RequestPostDto(postNumber);
    }

    private void validate(String postNumber) {
        // 간단한 유효성 체크
        if(!postNumber.endsWith("번")) {
            throw new RuntimeException();
        }
    }
}