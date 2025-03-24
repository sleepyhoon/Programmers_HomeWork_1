package io.global.config;

import io.domain.board.dao.BoardRepository;
import io.domain.board.dto.CreateBoardDto;
import io.domain.board.entity.Board;
import io.domain.member.dao.MemberRepository;
import io.domain.member.dto.CreateMemberDto;
import io.domain.member.entity.Member;
import io.domain.post.dao.PostRepository;
import io.domain.post.dto.CreatePostDto;
import io.domain.post.entity.Post;

public class PostConstructor {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public PostConstructor() {
        this.memberRepository = Container.getMemberRepository();
        this.postRepository = Container.getPostRepository();
        this.boardRepository = Container.getBoardRepository();

        dataInitialize(5, 4, 50);
    }

    private void dataInitialize(int memberCount, int boardCount, int postCount) {

        saveAdmin("admin", "admin1234", "admin", "example@gmail.com");
        CreateBoardDto boardDto = CreateBoardDto.of(1, "공지사항입니다", "공지사항");
        boardRepository.save(Board.of(boardDto));

        for (int i = 1; i <= memberCount; i++) {
            CreateMemberDto memberDto = CreateMemberDto.of(
                    "username " + i,
                    "password " + i,
                    "nickname " + i,
                    "email " + i
            );
            memberRepository.save(Member.of(memberDto));
        }

        for (int i = 1; i <= boardCount; i++) {
            boardDto = CreateBoardDto.of(
                    i,
                    "공지사항입니다 " + i,
                    "공지사항 " + i);
            boardRepository.save(Board.of(boardDto));
        }

        for (int i = 1; i <= postCount; i++) {
            CreatePostDto postDto = CreatePostDto.of(
                    i,
                    i % memberCount,
                    "제목입니다 " + i,
                    "내용입니다 " + i
            );
            postRepository.save(Post.of(postDto));
        }
    }

    public void saveAdmin(String username, String password, String nickname, String email) {
        Member admin = Member.ofAdmin(username, password, nickname, email);
        memberRepository.save(admin);
    }
}
