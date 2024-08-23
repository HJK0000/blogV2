package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardReponse {

    // V2 가 더 깔끔하다 실무에서는 이렇게 내부클래스를 만들어서 사용한다. 지금은 v1로 하자.
    @Data
    public static class DetailDTOV2 {
        // 화면에서 안쓰더라도 pk는 무조건 들고가도록 한다.
        private Integer boardId; // pk
        private String title;
        private String content;
        private Boolean isOwner;

        @Data
        public static class UserDTO {
            private Integer userId; // pk
            private String username;
        }


    }


    @Data
    public static class DetailDTO {
        // 화면에서 안쓰더라도 pk는 무조건 들고가도록 한다.
        private Integer boardId; // pk
        private String title;
        private String content;
        private Boolean isOwner;
        private Integer userId; // pk
        private String username;

        public DetailDTO(Board board, User sessionUser) {
            this.boardId = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isOwner = false;
            // service에서 응답을 1번만 해야하니까 board와 sessionUser 를 dto에 담아서 한번에 return 한다.
            // 권한체크
            if (board.getUser().getId() == sessionUser.getId()) {

                isOwner = true;

            }
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();

        }
    }

}
