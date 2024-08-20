package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 책임 : 식별자 요청 받기 & 응답하기
// 컨트롤러는 REPOSITORY에 의존하고 있다. (Repository가 없으면 일을 못하기 때문에)
@Controller // 식별자 요청을 받을 수 있다.
public class BoardController {

    /*
    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        System.out.println("BoardController 생성자");
        this.boardRepository = boardRepository;
    }
    */

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardRepository boardRepository;

    // url : http://localhost:8080/board/1/update
    // body : title=제목1변경&content=내용1변경
    // content-type : x-www-form-urlencoded
    // 버퍼 -> 보조스트림
    // 바구니에 담아서 줌. 10개를 한꺼번에 주면 받는사람은 하나씩 꺼내쓰면되고 주는사람은 기다리지않고 다른일할수있다.
    // 서로 통신하면 내 버퍼(Write buffer), 상대방 버퍼 (read buffer) -- bufferedWrite(->자바에서 편하게 쓰려고 prinwrite를 씀), bufferedRead
    // 수정하기 버튼을 클릭하면 브라우저의 application 버퍼에 먼저 쌓인다.
    // 스프링의 버퍼 (readBuffer)
    // request 로 버퍼에 접근할 수 있다.
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, @RequestParam("title") String title, @RequestParam("content") String content) {
        boardRepository.updateById(title, content, id);
        return "redirect:/board/" + id;

        //return "/board/detail" 하면 안됨 -> view에서 model.id 뿌려줘야하니까. 아까 request 객체에 담아서 보낸건 이미 사라짐ㄴ


    }


    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardRepository.deleteById(id);
        return "redirect:/board";
    }


    @PostMapping("/board/save")
    public String save(@RequestParam("title") String title, @RequestParam("content") String content) { // 스프링 기본전략 x-www-encoded
        // requestGetParameter안해도 이름이 폼태그의 name과 동일하면 자동으로 받아진다.
        boardRepository.save(title, content);

        return "redirect:/board"; //redirect는 다른 컨트롤러를 때리는 것

    }


    // get, post, put, delete
    // insert, delete, update 할때 일단 지금은 post 를 쓰자
    @GetMapping("/board") // 식별자 요청
    public String list(HttpServletRequest request) {

        //System.out.println(request.getRemoteAddr()); // 내가 내껄 때려서 정확한 ip 주소가 안뜸 0 0 0 0 0 이렇게 뜨고
        //System.out.println(request.getRequestURI());
        List<Board> boardList = boardRepository.findAll(); // request 객체 저장
        request.setAttribute("models", boardList);

        HttpSession session = request.getSession();
        session.setAttribute("num", 1);

        return "board/list"; // return의 위치가 templates 폴더로 고정되어 있다. / 가 없어도 바로 templates 폴더로 연결된다.
        // 확장자도 mustache 로 자동설정 되어있기 때문에 파일명만 적어주면 거기로 리턴한다.
    }

    // 1. 매서드 : Get
    // 2. 주소 : /board/1
    // 3. 응답 : board/detail

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board); // 1건 이니까 그냥 model로 적어주기!

        return "board/detail";
    }

    // 1. 매서드 : Get
    // 2. 주소 : /board/save-form
    // 3. 응답 : board/save-form

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    // 1. 매서드 : Get
    // 2. 주소 : /board/1/update-form
    // 3. 응답 : board/update-form

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) { // PathVariable은 정규표현식으로 처리하는걸 해줌 -> /board/1 이든 /board/2 이든 다 받아줌
        Board board = boardRepository.findById(id); // 만약 여기서 못찾으면 exception이 터짐
        request.setAttribute("model", board); // request 객체(model)에 담음
        return "board/update-form";
    }


}
