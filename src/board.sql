drop table reply;
drop sequence reply_seq;
drop table board;
drop sequence board_seq;

select * from board;
select * from reply;

update board
set hitcount = hitcount + 1
where boardnum = 1;

create table board (
                       boardnum number constraint boardnum_pk primary key,  -- 게시글번호
                       writer   varchar2(30) not null ,                     -- 글쓴이
                       title    varchar2(200) not null ,                    -- 제목
                       text     varchar2(1000) not null,                    -- 내용
                       hitcount number default 0,                           -- 조회수
                       regdate date default sysdate                         -- 등록일
);

create sequence board_seq;

insert into board  (boardnum, writer, title, text)
values (board_seq.nextval, '콩이', '오늘의 날씨는', '정말 구려용');

create table reply (
                       replynum number constraint replynum_pk primary key,  -- 댓글번호
                       boardnum number constraint reply_boardnum_fk         -- 참조키
                           references board(boardnum) on delete cascade,
                       writer varchar2(30) not null,                        -- 글쓴이
                       text varchar2(500) not null,                         -- 내용
                       regdate date default sysdate                         -- 등록일
);

create sequence reply_seq;

insert into reply (replynum, boardnum, writer, text)
values (reply_seq.nextval, 1, '차슈', '오늘의 날씨는?');