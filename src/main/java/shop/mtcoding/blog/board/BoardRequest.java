package shop.mtcoding.blog.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    // 요청DTO는 동일하게 생겨도 중복해서 만들기
    @Data
    public static class UpdateDTO { // title, content 2개만 담으면 된다.
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;

        // insert 가 아니라 UPDATE 이니까 투엔티티 없어도 댐

    }

    @Data
    public static class SaveDTO { // title, content 2개만 담으면 된다.
        //@Pattern(regexp = ) 정규표현식 패턴
        @NotEmpty
        private String title;
        @NotEmpty(message = "비워놓지마. (근데 이거 안써줘도 됨)")
        private String content;

        // insert 할 때는 toEntity 를 만든다.
        public Board toEntity(User sessionUser) { // 날짜는 엔티티에 @CreationTimeStamp 붙여주면 자동으로 들어간다.
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build(); // shift + enter
        }

    }


}
