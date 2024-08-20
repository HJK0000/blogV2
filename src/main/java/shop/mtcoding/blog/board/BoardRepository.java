package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // @Repository를 붙이면 스프링이 new를 해서 IoC(컬렉션 List자료형 같은거)에 저장한다. - inversion of container
public class BoardRepository {

    @Autowired // IoC에 있는 객체를 찾아온다.
    private EntityManager em;

    @Transactional
    public void updateById(String title, String content, int id) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?"); // where 뒤에오는 id는 url로 받아오고, set 뒤에오는 title, content는 body에 담아서 가져와라! (뷰)

        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);

        query.executeUpdate();

    }


    // 6번 게시글이 있는지 먼저 조회하게 있으면 삭제
    // @transactional 걸어서 남이 와서 수정못화게 할 수 있다.
    // 트렌젝션을 거는순간 다른 write 작업이 느려짐
    // 삭제하기 전 조회해서 있는지 먼저 확인하기 수정하거나 삭제할 수 있다.!!!
    // 쓸데없는 트렌젝션이 걸리면 느려진다...
    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?"); // Board.class는 조회할 때만 적어준다.
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // 상세보기 라고 이름지으면 안되는 이유가 게시글 상세보기할때만 사용하는게 아니어서! => 이름을 절대 기능명으로 적으면 안된다! 직관적으로 지어야 한다!
    // 조회니까 transactional을 안붙여도 된다.
    public Board findById(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class); // .Board.class 해주면 하이버네이트가 ob 해준다. 내가 파싱안해도 된다. relation도 해준다.
        query.setParameter(1, id);

        try {
            Board board = (Board) query.getSingleResult(); // (Board)로 다운캐스팅을 해줘야 에러가 안난다.
            return board;
        } catch (Exception e) {
            // 익셉션을 내가 잡은 것까지 배움 - 처리 방법은 v2에서 배우기 (터트리는건 내가..throw로 터트림!)
            throw new RuntimeException("게시글 id를 찾을 수 없습니다");
        }


    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class); // board 클래스로 매핑해서 받는다. => Object Mapping
        // 하이버네이트는 오브젝트 매핑뿐만 아니라 오브젝트 릴레이션 매핑도 해준다(매우 편리함)
        // @Entity 로 관리되는 애들만 매핑이 된다.
        List<Board> boardList = query.getResultList();


        return boardList;
        //datasource를 쓰고 있기 때문에, close 안해줘도 된다.

    }

    // insert 하기
    @Transactional // 변경요청할 때는 transactional을 붙여줘야한다.
    public void save(String title, String content) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, created_at) values(?,?,now())");
        query.setParameter(1, title); // position = ?의 순서
        query.setParameter(2, content);

        query.executeUpdate(); //insert, delete 등 wrtie 할때는 executeUpdate

    }
    // transational이 끝나기전에는 이 데이터가 안보인다.


}
