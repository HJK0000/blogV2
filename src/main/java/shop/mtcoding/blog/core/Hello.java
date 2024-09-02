package shop.mtcoding.blog.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 리플렉션 복습
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 런타임 시 실행됨
public @interface Hello {
}
