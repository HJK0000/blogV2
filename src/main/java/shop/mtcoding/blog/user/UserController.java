package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.core.Hello;

@RequiredArgsConstructor // final 이 붙은 애들이 생성자를 만들어준다. DI 가 엄청 간단해진다!
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;


    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); //session의 모든 걸 다 remove 시킨다. 내가 요청안했는데 서버가 자기 스스로 session의 정보를 삭제할 수 없다. 왜냐 키는 내가 내 쿠키에 들고 있으니까

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid UserRequest.loginDTO loginDTO, Errors errors) {
        User sessionUser = userService.로그인(loginDTO);
        // request 에서 session 접근해서 값 꺼내와도 되지만
        // http session 은 ioc 에 저장되어있으니 autowired 해서 꺼내쓰기
        session.setAttribute("sessionUser", sessionUser); // 헬스장에 로그인하면서 session 락카에 보관. 여기서 session에 값이 저장됨
        // 락카에는 객체를 다 넣을 필요가 없다. 그 유저의 pk만 넣어도 ㄱㅊ. 그 정보만 가지고 나중에 조회할 수 있!
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.joinDTO joinDTO) {

        userService.회원가입(joinDTO);

        return "redirect:/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @Hello
    @GetMapping("/login-form")
    public String loginForm() {
        System.out.println("loginForm 호출됨");
        return "user/login-form";
    }
}
