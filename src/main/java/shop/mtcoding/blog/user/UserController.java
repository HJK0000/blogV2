package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public String login(UserRequest.loginDTO loginDTO) {
        User sessionUser = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        // request 에서 session 접근해서 값 꺼내와도 되지만
        // http session 은 ioc 에 저장되어있으니 autowired 해서 꺼내쓰기
        session.setAttribute("sessionUser", sessionUser); // 헬스장에 로그인하면서 session 락카에 보관. 여기서 session에 값이 저장됨
        // 락카에는 객체를 다 넣을 필요가 없다. 그 유저의 pk만 넣어도 ㄱㅊ. 그 정보만 가지고 나중에 조회할 수 있기때문!
        return "redirect:/board";
    }

    @PostMapping("/join")
    public String join(UserRequest.joinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity()); //

        return "redirect:/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }
}
