package io.domain.board.service;

import io.domain.board.dto.CreateBoardDto;
import io.domain.board.dto.UpdateBoardDto;
import io.domain.post.dto.ResponsePostDto;
import io.domain.board.entity.Board;
import io.domain.post.entity.Post;
import io.domain.board.dao.BoardRepository;
import io.domain.member.dao.MemberRepository;
import io.global.auth.Session;
import io.global.exception.NoSuchBoardIdException;
import io.global.exception.NoSuchBoardNameException;
import io.global.exception.NotFoundMemberException;
import io.global.exception.NotFoundBoardException;
import io.global.exception.UnauthenticatedException;
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
                    String username = memberRepository.findUsernameById(post.getAuthorId()).orElseThrow(
                            () -> new NotFoundMemberException(post.getAuthorId() + "번 유저가 없습니다.")
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

    public void addPostToBoard(Post post) {
        Integer boardId = post.getBoardId();
        Board findBoard = boardRepository.getBoardById(boardId).orElseThrow(
                () -> new NotFoundBoardException("해당 id를 가진 board가 없습니다")
        );
        findBoard.addPost(post);
    }
}
