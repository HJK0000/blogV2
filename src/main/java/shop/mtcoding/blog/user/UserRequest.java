package shop.mtcoding.blog.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// 내부클래스로 관리!!
// 이렇게 하면 위험하지 않음
public class UserRequest {
    @Data // getter, setter, tostring 들고 있음
    public static class joinDTO {
        // static 이니까 new 하기전에 이 클래스는 static 에 뜬다.
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
        @NotEmpty
        private String email;

        // 매서드 만들기
        // 이 매서드는 DTO -> UserObject (유저 엔티티) 로 바꾸는 역할을 함!!
        public User toEntity() { // insert 하는건 toEntity로 만들어서 넣으면 완전 편리하고 코드가 심플해짐!!
            // 아래에서 유저객체 만들어서 return 함!
            return User.builder().username(username).password(password).email(email).build();
        }
    }
// 요청 바디 데이터 받기 위해 dto 생성함!!! (dto 만드는 목적)
    // 이렇게 만들어두면 관리하기 편하다.
    // 나중에 사용하기도 편함

    @Data // getter, setter, tostring 들고 있음
    public static class loginDTO {
        // static 이니까 new 하기전에 이 클래스는 static 에 뜬다.
        @Size(min = 2, max = 4) // 최소 2자, 최대 4자
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }
}
