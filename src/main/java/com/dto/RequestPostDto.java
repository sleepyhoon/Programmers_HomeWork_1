package com.dto;

public class RequestPostDto {
    private Integer postNumber;

    private RequestPostDto(String postNumber) {
        validate(postNumber);
        this.postNumber = Integer.valueOf(postNumber.substring(0, postNumber.length() - 1));
    }

    public Integer getPostNumber() {
        return postNumber;
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