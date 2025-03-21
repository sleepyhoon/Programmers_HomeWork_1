package com.domain.service;

import com.domain.dto.board.CreateBoardDto;
import com.domain.dto.board.UpdateBoardDto;
import com.domain.dto.post.ResponsePostDto;
import com.domain.entity.Board;
import com.domain.entity.Post;
import com.domain.repository.BoardRepository;
import com.domain.repository.MemberRepository;
import com.global.auth.SessionContext;
import com.global.exception.NoSuchBoardIdException;
import com.global.exception.NoSuchBoardNameException;
import com.global.exception.NoSuchMemberException;
import java.util.List;

public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public List<ResponsePostDto> selectAllPosts(String boardName) {
        List<Post> posts = boardRepository.selectAllPosts(boardName);
        if (posts == null) {
            throw new NoSuchBoardNameException(boardName + " 이름을 가진 board는 없습니다.");
        }
        return posts.stream()
                .map(post -> {
                    if (SessionContext.currentUserIsNull()) {
                        return ResponsePostDto.of(post, null);
                    }
                    String username = memberRepository.getUsernameById(post.getMemberId()).orElseThrow(
                            () -> new NoSuchMemberException(post.getMemberId() + "번 유저가 없습니다.")
                    );
                    return ResponsePostDto.of(post, username);
                })
                .toList();
    }

    public Integer create(CreateBoardDto boardDto) {
        Board board = Board.of(boardDto);
        boardRepository.save(board);
        return board.getId();
    }

    public void delete(Integer boardId) {
        if (boardRepository.isExistId(boardId)) {
            throw new NoSuchBoardIdException(boardId + "번 board는 없습니다.");
        }
        boardRepository.delete(boardId);
    }

    public void update(UpdateBoardDto updateBoardDto) {
        if (!boardRepository.isExistId(updateBoardDto.getId())) {
            throw new NoSuchBoardIdException(updateBoardDto.getId() + "번 board는 없습니다.");
        }
        Board board = Board.of(updateBoardDto);
        boardRepository.update(board);
    }

}
