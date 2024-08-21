package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity // DB에서 조회하면 자동 매핑이됨
@Getter
@Setter
@Table(name = "board_tb") // 테이블 명 설정해주기
public class Board {


    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment 설정. 시퀀스 설정
    @Id // PK 설정
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    private Timestamp createdAt;


    // fk
    @ManyToOne(fetch = FetchType.LAZY)
    // user 조회를 안한다. fk로 1을 들고있으니까 user 에 id만 넣어서 객체생성
    // eager 로 하면 user 조회한다. user객체의 모든 정보를 조회해서 넣어둔다.
    private User user;

    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
