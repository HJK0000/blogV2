package shop.mtcoding.blog.core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog.core.error.ex.*;
import shop.mtcoding.blog.core.util.Script;


// 만약 ControllerAdvice로 걸면 아래에 @Responsebody 걸어주면된다.
@RestControllerAdvice // 모든 throw는 이제 애한테 날아온다.
public class GlobalExceptionHandler {

    // 유효성 검사 실패 (잘못된 클라이언트의 요청)
    @ExceptionHandler(Exception400.class) // 이 예외가 터지면 이제 400이 터짐
    public String ex400(Exception e) {
        return Script.back(e.getMessage());
    }

    // 인증 실패 (클라이언트가 인증없이 요청했거나, 인증을 하거나 실패했거나)
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception e) { // 401은 back 보다 로그인 페이지로 보내주는게 좋다.
        return Script.href("인증되지 않았습니다", "/login-form"); //인증할때는 에러메시지 그대로 보여주지말기.
    }

    // 권한 실패 (인증은 되어 있는데, 삭제하려는 게시글이 내가 적은게 아니다)
    @ExceptionHandler(Exception403.class)
    public String ex403(Exception e) {
        return Script.back(e.getMessage());
    }

    // 서버에서 리소스(자원)를 찾을 수 없을때
    @ExceptionHandler(Exception404.class)
    public String ex404(Exception e) {
        return Script.back(e.getMessage());
    }

    // 서버에서 심각한 오류가 발생했을때 (알고 있을 때)
    @ExceptionHandler(Exception500.class)
    public String ex500(Exception e) {
        return Script.back(e.getMessage());
    }

    // 서버에서 심각한 오류가 발생했을때 (모를 때)
    @ExceptionHandler(Exception.class) // 그냥 exception
    public String ex(Exception e) {
        return Script.back(e.getMessage());
    }

}
