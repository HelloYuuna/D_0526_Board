package net.scit.dao;

import net.scit.vo.Board;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * projectName     :D_0526_Board
 * fileName        :BoardDAO
 * author          :yuuna
 * since           :2022/05/26
 */
public class BoardDAO {
    private final SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();


    /**
     * 게시글 등록
     * insert into > commit 필요, resultType 없음
     * @param board 게시판
     * @return int result
     */
    public int writeBoard(Board board) {
        SqlSession session = factory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        int result = mapper.writeBoard(board);
        session.commit();

        return result;
    }
    public int getCount() {
        SqlSession session = factory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        return mapper.getCount();
    }

    public List<Board> listBoard() {
        SqlSession session = factory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        return mapper.listBoard();
    }

    public Board readBoard(int boardnum) {
        SqlSession session = factory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        return mapper.readBoard(boardnum);
    }

    public int deleteBoard(int boardnum) {
        SqlSession session = factory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        int result = mapper.deleteBoard(boardnum);
        if(result == 1) {
            session.commit();
            return 1;
        }

        session.rollback();
        return 0;
    }

    public int incrementHitcount(int boardnum) {
        SqlSession session = factory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        int result = mapper.incrementHitcount(boardnum);

        if(result == 1) {
            session.commit();
            return 1;
        }

        session.rollback();
        return 0;
    }

    public int updateBoard(Board board) {
        SqlSession session = factory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);

        int result = mapper.updateBoard(board);

        if(result == 1) {
            session.commit();
            return 1;
        }

        session.rollback();
        return 0;
    }
}
