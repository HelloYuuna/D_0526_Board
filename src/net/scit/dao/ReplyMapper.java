package net.scit.dao;

import net.scit.vo.Reply;

import java.util.List;

/**
 * projectName     :D_0526_Board
 * fileName        :ReplyMapper
 * author          :yuuna
 * since           :2022/05/26
 */
public interface ReplyMapper {
    /* 댓글 목록 */
    public List<Reply> listReply(int boadnum);

    /* 댓글 쓰기 */
    public int writeReply(Reply reply);                 //boardnum을 얻기위해

    /* 댓글 삭제 */
    public int deleteReply(int replynum);

    /* 댓글 수정 */
    public int updateReply(Reply reply);

}
