package shop.mtcoding.blog.board;


// C -> S -> R

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception403;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    // DI 생성자 주입
    private final BoardRepository boardRepository;

    // 수정하기
    // 게시글 목록보기 해보기

    // v3 페이징이랑 댓글쓰기, 검색 배울예정
    // 파이어베이스, 몽고디비, 웹소켓

    public List<Board> 게시글목록보기() { // 나중에 페이징 처리하는거 여기 추가할 예정
        List<Board> boardList = boardRepository.findAll();

        return boardList;
    }


    @Transactional
    public void 게시글삭제(int id, User sessionUser) {
        // 1. 컨트롤러에서 게시글 id 받기 & 권한체크에 필요한 sessionUser 도 받기

        // 2. 게시글 존재 여부 확인 (404)
        Board board = boardRepository.findById(id);

        // 3. 권한 체크 (내가 쓴 글인지 확인하기. 403(권한없음))
        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("권한이 없습니다.");

        }
        // 4. 게시글 삭제
        boardRepository.deleteById(id);

    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        // 누가 적었는지 알아야하니까 session이 필요
        boardRepository.save(saveDTO.toEntity(sessionUser));

        // sessionUser 인증체크는 컨트롤러에서 함
        // 그래서 여기 매개변수로 null이 들어올 경우는 없음
        // sessionUser == null 이런조건은 컨트롤러에서


    }


    // 기능명은 한글로 적는다.
    // 매서드는 행위 -> 동사
    public BoardReponse.DetailDTO 상세보기(int id, User sessionUser) { // 매개변수는 컨트롤러로부터 받는다.
        // final Httpsession으로 받으면 session.getAttriute해서 user로 다운캐스팅 해야해서 귀찮아지니까 이렇게 안하고
        // 세션 인증 체크는 컨트롤러에서 할거고 지금 서비스에서는 권한 체크(Board의 user랑 session의 user가 일치하는지)
        // 를 할거니까 sessionuSER는 매개변수로 받아도 충분
        Board board = boardRepository.findById(id); // 조인한 쿼리 쓰니까 (Board - User) 둘다 있음
        // 여기 null 처리 할 필요가 없음. findById 에서 null 이 들어오면 throw로 던지니까 서비스에서 board 값으로 null을 받을 일이 없음

        return new BoardReponse.DetailDTO(board, sessionUser); // board 와 boolean 2개 리턴 불가능 해 dto 만들기

    }

}