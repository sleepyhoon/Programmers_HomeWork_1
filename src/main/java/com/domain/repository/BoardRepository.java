package com.domain.repository;

import com.domain.entity.Board;
import com.domain.entity.Post;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BoardRepository {
    private final HashMap<Integer, Board> boardHashMap = new HashMap<>();
    private Integer mapIndex = 1;

    public void save(Board board) {
        board.setId(mapIndex);
        boardHashMap.put(mapIndex++, board);
    }

    public void update(Board board) {
        boardHashMap.put(board.getId(),board);
    }

    public List<Post> selectAllPosts(String boardName) {
        Collection<Board> values = boardHashMap.values();
        for (Board value : values) {
            if(value.getTitle().equals(boardName)) {
                return value.getPosts();
            }
        }
        return null;
    }

    public void delete(Integer boardId) {
        boardHashMap.remove(boardId);
    }

    public boolean isExistId(Integer boardId) {
        Set<Integer> keySet = boardHashMap.keySet();
        return keySet.contains(boardId);
    }

    public Board getBoard(Integer id) {
        return boardHashMap.get(id);
    }
}
