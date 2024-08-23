package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

//@SpringBootTest // C R e h2 -> 모든 레이어를 메모리에 다 올리고 테스트할 때 사용하는 어노테이션

@DataJpaTest // h2, em
@Import(BoardRepository.class) // boardRepository
public class BoardRepositoryTest { // 클래스명은 Test 붙여서 짓는다 (컨벤션)

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findByTitle_test() {
        //given
        String title = "제목1";
        //when
        Board board = boardRepository.findByTitle(title);
        //eye
        System.out.println(board.getUser().getId());

    }

    @Test
    public void findByIdV2_test() {
        //given
        int id = 1;

        Board board = boardRepository.findByIdV2(id);
        // board를 조회하면서 user의 정보를 들고 오지만
        // 영속객체로 등록할 때, 별칭으로 필드이름이 지어지기 때문에 꺼내쓸 수 없다.
        // 그래서 아래 매서드에서 select 가 한번 더 실행된다.
        System.out.println(board.getUser());


/*        //when
        Board board = boardRepository.findByIdV2(id);
        Board board2 = boardRepository.findByIdV2(id);
        //eye
        System.out.println(board.getUser().getUsername());
        System.out.println(board2.getUser().getUsername()); // 하이버네이트에서 가져오기 때문에 여기서 select 실행안됨 ?*/
    }

    @Test
    public void updateById_test() {
        //given
        int id = 1;
        String title = "제목1변경";
        String content = "내용1변경";

        //when

        boardRepository.updateById(title, content, id);
        //then
        Board board = boardRepository.findById(id);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목3변경");
    }

    @Test
    public void deleteById_test() {

        //given
        // 삭제할 때 굳이 3을 잡지 말기.
        // 일관성 있게 1을 삭제하자
        int id = 6;

        //when
        boardRepository.deleteById(id);

        //eye
/*        try {
            boardRepository.findById(id);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("게시글 id를 찾을 수 없습니다");
        }*/

    }

    @Test
    public void findById_test() {
        //given
        // 가짜콩. 1단계 콩갈기 하지말고 걍 2단계 테스트할 때 10cm 이하의 콩을 넣어준다.
        int id = 1; // 6 넣으면 내가 예외처리해둔 RuntimeException 이 나온다. 내가 직접 잡아서 관리!!

        //when
        Board board = boardRepository.findById(id);

        //eye
        //then에서 assertion으로 상태검증 하는건 나중에 할거임
        //왜 eye를 썼어요? 하면 초보여서 눈으로 확인 먼저 했다고 하면 됨!
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());

        // then (코드)
        // 코드로 검증
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");

    }

    @Test
    public void findAll_test() {
        // given
        // findAll 을 실행할 때 필요한 매개변수가 온다.
        // findAll 매서드에는 매개변수가 필요없으니 비워둔다.

        // when
        System.out.println("1. 첫번째 조회");
        List<Board> boardList = boardRepository.findAll();
        System.out.println("userId : " + boardList.get(0).getUser().getId());
        System.out.println("==============");

        // eye
        System.out.println("2. 레이지 로딩"); // 목록에는 필요없고 상세보기 할 때 필요하다

        // select 1번 하는 경우
        //System.out.println("username : " + boardList.get(0).getUser().getUsername());
        //System.out.println("username : " + boardList.get(1).getUser().getUsername());
        //System.out.println("username : " + boardList.get(2).getUser().getUsername());


        // select 2번 하는 경우
        System.out.println("username : " + boardList.get(0).getUser().getUsername());
        System.out.println("username : " + boardList.get(4).getUser().getUsername());


    }

    // 테스트 매서드에서는 매개변수를 사용할 수 없다.
    // 매서드명_test : 컨벤션
    @Test
    public void save_test() { // 매서드명_test 로 매서드 이름을 짓는다 (컨벤션)
        // given (매개변수를 강제로 만들기)
        String title = "제목1";
        String content = "내용1";

        // when
        //boardRepository.save(title, content);

        // eye (눈으로 확인)

        // eye(눈으로 확인)

    }
}
