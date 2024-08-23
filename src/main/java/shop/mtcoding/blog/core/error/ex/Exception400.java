package shop.mtcoding.blog.core.error.ex;

// exception을 핸들러로 구분지음
// 400번대는 모두 클라이언트가 요청을 잘못했을때 터트리는 것
// 유효성 검사 할 때 터트릴 것
public class Exception400 extends RuntimeException {

    public Exception400(String message) { // "아이디의 길이가 깁니다"
        super(message); // 부모한테 메시지를 넘김
    }
}
