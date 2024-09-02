package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.core.error.ex.Exception401;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    // 회원가입 시 username 중복체크
    public User findByUsername(String username) {

        // 조회 쿼리
        Query query = em.createQuery("select u from User u where u.username=:username", User.class);
        query.setParameter("username", username);

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null; // 못찾으면 null 을 return 함. 그럼 null을 받아가지고 비지니스 처리는 서비스에서 함!
        }


    }


    // 로그인
    public User findByUsernameAndPassword(String username, String password) {

        // 조회 쿼리
        Query query = em.createQuery("select u from User u where u.username=:username and u.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        // 연관된 엔티티가 없으니까 object mapping (연관된 엔티티가 있으면 object relation mapping 을 함)
        // 배열로 받지않게 User.class 추가

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            throw new Exception401("인증되지 않았습니다.");
            // ()안에 e.getMessage 하면 안됨. 그건 서버측에만 기록하고 사용자에게 알려주지 말기
        }


    }


    // repository 에는 기능명을 넣지말자
    // join 이라고 하지말고 save 로 짓기
    public void save(User user) { // 1. 비영속 user
        System.out.println("담기기전 : " + user.getId()); // 이때 id 는 null
        /*
        // 날짜
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        User user = new User(); //pk 없이 user 객체 생성
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        */

        em.persist(user); // insert 쿼리가 날아감. id 가 없으니까 insert
        // persist 로 em에 담기면 영속 user 가 됨!!!! 담기면 insert 된다!! 동기화 된다고!!!
        System.out.println("담긴 후 : " + user.getId()); // user 가 영속객체가 되었음. getId 하면 id가 나옴
    }


    public User findById() {
        Query query = em.createNativeQuery("select * from user_tb where id = 1");
        Object[] obs = (Object[]) query.getSingleResult();
        System.out.println(obs[0]);
        System.out.println(obs[1]);
        System.out.println(obs[2]);
        System.out.println(obs[3]);
        System.out.println(obs[4]);

        User user = new User();
        user.setId((Integer) obs[0]);
        user.setCreatedAt((Timestamp) obs[1]);
        user.setEmail((String) obs[2]);
        user.setPassword((String) obs[3]);
        user.setUsername((String) obs[4]);

        return user;
    }
}