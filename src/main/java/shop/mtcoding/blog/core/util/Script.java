package shop.mtcoding.blog.core.util;

public class Script {

    public static String back(String msg) {

        String errMsg = """
                <script>
                    alert('$msg');
                    history.back(); 
                </script>
                """.replace("$msg", msg);

        return errMsg;
        // 자바스크립트 문자를 만들어서 리턴하는 것
    }

    // 404 페이지를 찾을 수 없습니다. 라는 페이지를 만드는 것보다
    // alert 창을 띄우고 이동시키는게 낫다 ㅎ
    // 메시지를 하나 띄우고 화면 이동해주는 애
    public static String href(String msg, String url) {

        String errMsg = """
                <script>
                    alert('$msg');
                   location.href = '$url';
                </script>
                """.replace("$msg", msg)
                .replace("$url", url);
        return errMsg;
        // 자바스크립트 문자를 만들어서 리턴하는 것
    }

}
