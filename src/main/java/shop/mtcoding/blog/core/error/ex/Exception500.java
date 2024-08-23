package shop.mtcoding.blog.core.error.ex;

public class Exception500 extends RuntimeException {

    public Exception500(String message) { // "아이디의 길이가 깁니다"
        super(message); // 부모한테 메시지를 넘김
    }

}
