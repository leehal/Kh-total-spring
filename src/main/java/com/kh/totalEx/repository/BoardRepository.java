package com.kh.totalEx.repository;


import com.kh.totalEx.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<List<Board>> findByTitleContaining(String keyword);
    Optional<Board> findByTitleAndContent(String Title, String Content);

//    제목 검색

}

// 게시판 조회
// 제목 내용 조회


