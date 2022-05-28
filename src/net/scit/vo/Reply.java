package net.scit.vo;

/**
 * projectName     :D_0526_Board
 * fileName        :Reply
 * author          :yuuna
 * since           :2022/05/26
 */
public class Reply {
    private int replynum;
    private int boardnum;
    private String writer;
    private String text;
    private String regdate;

    public Reply() {
    }

    public Reply(int replynum, int boardnum, String writer, String text, String regdate) {
        this.replynum = replynum;
        this.boardnum = boardnum;
        this.writer = writer;
        this.text = text;
        this.regdate = regdate;
    }

    public int getReplynum() {
        return replynum;
    }

    public void setReplynum(int replynum) {
        this.replynum = replynum;
    }

    public int getBoardnum() {
        return boardnum;
    }

    public void setBoardnum(int boardnum) {
        this.boardnum = boardnum;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String wirter) {
        this.writer = wirter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
//        "(" + boardnum+ "-" + replynum + ") " +
//                writer + "   " + text + "   " + regdate + "\n";
        return String.format("(%d-%d) %s %s %s",boardnum,replynum,writer,text,regdate);
    }
}
