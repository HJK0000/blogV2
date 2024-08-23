package shop.mtcoding.blog.core.error.ex;

// 인증관련
public class Exception401 extends RuntimeException {

    public Exception401(String message) {
        super(message); // 부모한테 메시지를 넘김
    }

}
