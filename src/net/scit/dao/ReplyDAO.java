package net.scit.dao;

import net.scit.vo.Reply;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * projectName     :D_0526_Board
 * fileName        :ReplyDAO
 * author          :yuuna
 * since           :2022/05/26
 */
public class ReplyDAO {
    private final SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

    public int deleteReply(int replynum) {
        SqlSession session = factory.openSession();
        ReplyMapper mapper = session.getMapper(ReplyMapper.class);
        int result = mapper.deleteReply(replynum);

        if(result == 1) {
            session.commit();
            return 1;
        }

        session.rollback();
        return 0;
    }

    public int updateReply(Reply reply) {
        SqlSession session = factory.openSession();
        ReplyMapper mapper = session.getMapper(ReplyMapper.class);
        int result = mapper.updateReply(reply);

        if(result == 1) {
            session.commit();
            return 1;
        }

        session.rollback();
        return 0;
    }

    public List<Reply> listReply(int boardnum) {
        SqlSession session = factory.openSession();
        ReplyMapper mapper = session.getMapper(ReplyMapper.class);

        return mapper.listReply(boardnum);
    }

    public int writeReply(Reply reply) {
        SqlSession session = factory.openSession();
        ReplyMapper mapper = session.getMapper(ReplyMapper.class);
        int result = mapper.writeReply(reply);

        if(result == 1) {
            session.commit();
            return 1;
        }

        session.rollback();
        return 0;
    }
}
