package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class SaveDTO { // title, content 2개만 담으면 된다.
        private String title;
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
