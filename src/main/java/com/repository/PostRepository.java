package com.repository;

import com.entity.Post;
import java.util.HashMap;

public class PostRepository {
    private final HashMap<Integer, Post> postHashMap = new HashMap<>();
    private Integer mapIndex = 1;

    // 저장
    public void save(Post post) {
        post.setId(mapIndex);
        System.out.println(post + "을 저장합니다.");
        postHashMap.put(mapIndex++, post);
    }

    // 조회
    public Post get(Integer postNumber) {
        return postHashMap.get(postNumber);
    }

    // 수정
    public void update(Post post) {
        System.out.println(post + "을 저장합니다.");
        postHashMap.put(post.getId(), post);
    }

    // 삭제
    public void delete(Integer number) {
        postHashMap.remove(number);
    }
}
