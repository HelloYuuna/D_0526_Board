package net.scit.dao;

import net.scit.vo.Board;

import java.util.List;
import java.util.Map;

/**
 * projectName     :D_0526_Board
 * fileName        :BoardMapper
 * author          :yuuna
 * since           :2022/05/26
 */
public interface BoardMapper {
    /* 게시글 작성 */
    public int writeBoard(Board board);

    /* 게시글 전체 목록 */
    public List<Board> listBoard();

    /* 게시글 전체 개수 */
    public int getCount();

    /* 게시글 읽기 */
    public Board readBoard(int boardmum);

    /* 게시글 검색 */
    public List<Board> searchBoard(Map<String, Object> searchBoardbyMap);
//    public Board searchBoard(int boardnum);

    /* 게시글 삭제 */
    public int deleteBoard(int boardnum);

    /* 게시글 수정 */
    public int updateBoard(Board board);

    /* 게시글 조회수 증가 */
//    update board
//    set hitcount = hitcount + 1
//    where boardnum = #{boardnum}
    public int incrementHitcount(int boardnum);

}
