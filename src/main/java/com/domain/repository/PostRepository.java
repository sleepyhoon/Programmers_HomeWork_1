package com.domain.repository;

import com.domain.entity.Post;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PostRepository {
    private final HashMap<Integer, Post> postHashMap = new HashMap<>();
    private Integer mapIndex = 1;

    // 저장
    public void save(Post post) {
        post.setId(mapIndex);
        postHashMap.put(mapIndex++, post);
    }

    // 조회
    public Post get(Integer postId) {
        return postHashMap.get(postId);
    }

    // 전체 조회
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>(postHashMap.values());
        Collections.sort(list);
        return list;
    }

    // 수정
    public void update(Post post) {
        postHashMap.put(post.getId(), post);
    }

    // 삭제
    public void delete(Integer number) {
        postHashMap.remove(number);
    }

    public boolean contains(Integer number) {
        return postHashMap.containsKey(number);
    }
}
