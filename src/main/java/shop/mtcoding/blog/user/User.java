package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

// 긁어서 지피티한테 컬럼에서 사용할 수 있는 거 다 알려달라고 하면 알려줌
@Entity
@NoArgsConstructor
@Table(name = "user_tb")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username; // 아이디
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @CreationTimestamp
    // em.persist 나 JPQL 쓰면 이 어노테이션 쓰기! (네이티브 쿼리는 안먹음)
    private Timestamp createdAt;

    // 컬렉션이 들어오면 builder가 터지기 때문에 클래스보다는 생성자 위에 붙여서 쓰기!
    @Builder
    public User(Integer id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
}
