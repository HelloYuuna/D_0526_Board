package net.scit.ui;

import net.scit.dao.BoardDAO;
import net.scit.dao.ReplyDAO;
import net.scit.vo.Board;
import net.scit.vo.Reply;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * projectName     :D_0526_Board
 * fileName        :BoardUI
 * author          :yuuna
 * since           :2022/05/26
 */
public class BoardUI {
    String choice;
    Scanner scanner = new Scanner(System.in);
    BoardDAO boardDAO = new BoardDAO();
    ReplyDAO replyDAO = new ReplyDAO();
    public void start() {

        while(true) {
            mainMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1" : input(); break;
                case "2" : list(); break;
                case "3" : read(); break;
                case "4" : delete(); break;
                case "5" : update(); break;
                case "6" : search(); break;
                case "0" :
                    out.println("프로그램 종료!");
                    System.exit(0); break;
                default:
                    out.println("메뉴 다시 선택!");
            }
        }
    }

    public void mainMenu() {
        out.println("----------------------------");
        out.println("\t [ 모든 게시판 ]");
        out.println("----------------------------");
        out.println("\t 1. 게시글 등록");                    // 등록
        out.println("\t 2. 전체 목록 조회");                 // 게시글 리스트
        out.println("\t 3. 게시글 읽기");                   // 게시글 읽기
        out.println("\t 4. 게시글 삭제");
        out.println("\t 5. 게시글 수정");
        out.println("\t 6. 게시글 검색");
        out.println("\t 0. 프로그램  종료");
        out.println("----------------------------");
        out.print("\t 선택> ");
    }

    /**
     * 게시글 쓰기 :
     * seq가 겹쳐서는 안됨
     */
    public void input() {
        out.println("\n\t [ 새로운 게시글 등록 ]");
        out.println("----------------------------");

        out.print("작성자: ");
        String writer = scanner.nextLine();

        out.print("제목: ");
        String title = scanner.nextLine();

        out.print("내용: ");
        String text = scanner.nextLine();

        Board board = new Board();
        board.setWriter(writer);
        board.setTitle(title);
        board.setText(text);

        int result  = boardDAO.writeBoard(board);

        out.println(result + "개의 게시글이 등록되었습니다.!");

    }

    public void list() {
        out.println("\n\t [ 게시글 전체 목록 ]");
        out.println("----------------------------");
        // count로 등록된 게시글 존재 여부 보여주기
        int result = boardDAO.getCount();
        if(result == 0) {
            out.println("등록된 게시글이 없습니다. 먼저 게시글을 등록해주세요!");
            return;
        }
        out.print("전체 글 개수 : " + result + "개");
        out.println("\n글번호    작성자  제목        조회수  등록일");
        out.println("----------------------------");
        List<Board> boardList = boardDAO.listBoard();
        boardList.forEach(System.out::println);
        out.println();
    }

    public void read() {
        out.println("\n\t [ 게시글 읽기 ]");

        /* 등록된 게시글 존재 유무 */
        int result = boardDAO.getCount();
        if(result == 0) {
            out.println("등록된 게시글이 없습니다. 먼저 게시글을 등록해주세요!");
            return;
        }

        while (true) {
            out.print("게시글 번호: ");
            int boardnum = scanner.nextInt();

            /* serching boardnum */
            Board readBoard = boardDAO.readBoard(boardnum);
            if (readBoard == null) {
                out.println("해당하는 게시글이 없습니다.");
                continue;
            }

            boardDAO.incrementHitcount(boardnum);
            out.println("----------------------------");
            out.println("글번호  작성자 제목 조회수  등록일");
            out.println("----------------------------");
            out.println(readBoard);
            out.println("내용: " +readBoard.getText());
            out.println("----------------------------");
            replyStart(boardnum);

            out.println();
            scanner.nextLine();
            return;
        }

    }

    private void replyStart(int boardnum) {
        scanner.nextLine();
        out.println("\n\t [ 댓글 목록 ]");
        out.println("----------------------------");

        List<Reply> listReply = replyDAO.listReply(boardnum);
        if (listReply.isEmpty()) {
            out.println("등록된 댓글이 없습니다.");
        }
        listReply.forEach(out::println);

        while(true) {
            replyMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    writeReply(boardnum); break;
                case "2":
                    updateReply(boardnum); break;
                case "3":
//                    deleteReply(boardnum); break;
                case "0":
                    out.println("상위메뉴로 이동합니다.");
                    start(); return;
                default:
                    out.println("메뉴 다시 선택!");
            }
        }

    }

    private void updateReply(int boardnum) {
        out.print("수정할 댓글번호: ");
        Reply reply = new Reply();
        int replynum = scanner.nextInt();
        reply.setReplynum(replynum);

        out.print("수정할 내용(500자): ");
        String text = scanner.nextLine();
        reply.setText(text);

        int result = replyDAO.updateReply(reply);
        if(result == 1) {
            out.println("댓글" + result + "건이 수정되었습니다.");
        } else {
            out.println("댓글 수정에 실패하였습니다.");
        }

        /* 댓글 등록후 보여주는 게시판 내용 */
        showByBoardnum(boardnum);
    }

    private void writeReply(int boardnum) {
        out.print("작성자: ");
        String writer = scanner.nextLine();

        out.print("내용(500자): ");
        String text = scanner.nextLine();

        Reply reply = new Reply();
        reply.setBoardnum(boardnum);
        reply.setWriter(writer);
        reply.setText(text);

        int result = replyDAO.writeReply(reply);
        if(result == 1) {
            out.println("댓글" + result + "건이 등록되었습니다.");
        } else {
            out.println("댓글 등록에 실패하였습니다.");
        }

        /* 댓글 등록후 보여주는 게시판 내용 */
        showByBoardnum(boardnum);

    }

    /**
     * 댓글 등록 삭제 수정후에도 해당 게시판내용은 계속 보여줘야함
     * 히트카운트는 증가하지 않는다.
     * @param boardnum 게시판번호:seq
     */
    private void showByBoardnum(int boardnum) {
        Board readBoard = boardDAO.readBoard(boardnum);
        out.println("----------------------------");
        out.println("글번호 / 작성자 / 제목 / 조회수 / 등록일");
        out.println("----------------------------");
        out.println(readBoard);
        out.println("내용: " +readBoard.getText());
        out.println("----------------------------");
        replyStart(boardnum);
    }

    private void replyMenu() {
        out.println("----------------------------");
        out.println("1)댓글 등록 2)댓글 수정 3)댓글 삭제 0) 상위 메뉴");
        out.print("선택> ");
    }

    public void delete() {
        out.println("\n\t [ 게시글 삭제 ]");
        out.println("----------------------------");

        int result = boardDAO.getCount();
        if(result == 0) {
            out.println("등록된 게시글이 없습니다. 먼저 게시글을 등록해주세요!");
            return;
        }

        while (true) {
            out.print("삭제하려는 게시글 번호: ");
            int boardnum = scanner.nextInt();

            /* serching boardnum */
            Board readBoard = boardDAO.readBoard(boardnum);
            if (readBoard == null) {
                out.println("번호에 해당하는 게시글이 없습니다. 다시 입력해주세요.");
                continue;
            }
            result = boardDAO.deleteBoard(boardnum);
            if(result != 1) {
                out.println("삭제에 실패하였습니다.");
            }

            out.println(result + "건 삭제완료");

            scanner.nextLine();
            return;
        }

    }

    public void update() {
    }

    public void search() {
    }

}
