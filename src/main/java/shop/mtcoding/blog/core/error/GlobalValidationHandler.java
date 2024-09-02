package shop.mtcoding.blog.core.error;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component // 모든 어노테이션 안에 있는 그 메타 어노테이션! 이거 붙여야지 이 클래스가 new가 된다.
@Aspect // AOP 등록
public class GlobalValidationHandler {

    // 유효성 검사
    // 매서드 전에만 발동하면 되니까 before 붙여주기
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validCheck(JoinPoint jp) {
        Object[] args = jp.getArgs(); // 매서드의 매개변수가 args 에 담긴다.
        

        // 수정 실행해서 확인
        System.out.println("사이즈 : " + args.length);
        // 담겼는지 확인
        for (Object arg : args) {
            System.out.println(arg);
        } // 확인하는 코드


    }


    // get 매핑 풀네임 : org.springframework.web.bind.annotation.GetMapping
    @Around("@annotation(shop.mtcoding.blog.core.Hello)")
    //@Before("@annotation(org.springframework.web.bind.annotation.GetMapping)") // 리플렉션 사용. 어노테이션 이름으로 찾아서 때리는게 좋다.
    public Object hello1(ProceedingJoinPoint jp) throws Throwable {// 조인포인트를 통해서 매개변수에 접근할 수 있다.
        System.out.println("AOP hello before 호출됨");
        Object proceed = jp.proceed(); // alt enter
        System.out.println("AOP hello after 호출됨");
        System.out.println(proceed);
        return proceed; // around는 결과값을 object 타입으로 ds에게 넘겨줘야하니까 이렇게 씀
    }

}
