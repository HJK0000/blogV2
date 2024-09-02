package shop.mtcoding.blog.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.mtcoding.blog.core.interceptor.LoginInterceptor;

@Configuration // IoC에 저장됨. 설정파일에는 configuration 붙여줌
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터 ds와 c사이에 등록
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/**");


    }
}
