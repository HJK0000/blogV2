package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception400;

@RequiredArgsConstructor
@Service
public class UserService {

    //service -> repository 에 의존
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.joinDTO joinDTO) {
        // 동일한 유저가있으면 안되니 db 먼저 조회해야함

        User oldUser = userRepository.findByUsername(joinDTO.getUsername());

        // 회원가입 실패 400 -> 클라이언트 잘못이니까
        if (oldUser != null) {
            throw new Exception400("이미 존재하는 유저네임입니다.");
        }
        //throw 터지면 메서드 닫힌다.
        userRepository.save(joinDTO.toEntity());
    }

    public User 로그인(UserRequest.loginDTO loginDTO) {

        User user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        return user;
        // 조회가 안되면 401을 터트려야함


    }
}
