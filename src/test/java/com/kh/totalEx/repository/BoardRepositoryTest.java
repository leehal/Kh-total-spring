package com.kh.totalEx.repository;

import com.kh.totalEx.entity.Board;
import org.apache.tomcat.jni.LibraryNotFoundError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 insert")
    public void createBoard() {
        for (int i = 0; i <= 10; i++) {
            Board board = new Board();
            board.setTitle("안녕" + i);
            board.setContent("반갑" + i);
            board.setRegDate(LocalDateTime.now());
            boardRepository.save(board);
        }

    }

    @Test
    @DisplayName("게시판 조회")
    public void findByTitleContaining() {
        this.createBoard();
        Optional<List<Board>> boardList = boardRepository.findByTitleContaining("녕");
        for (Board board : boardList.get()) {
            System.out.println(board.toString());
        }
    }
}