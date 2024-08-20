package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
