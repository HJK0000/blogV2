package shop.mtcoding.blog.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest // h2, em
@Import(UserRepository.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_test() {

        String username = "haha";
        User user = userRepository.findByUsername(username);

        System.out.println("user = " + user);
    }


    @Test
    public void findByUsernameAndPassword_test() {

        String username = "love";
        String password = "1234";

        User user = userRepository.findByUsernameAndPassword(username, password);
        System.out.println(user.getUsername());
        System.out.println(user.getUsername());
    }

    @Test
    public void save_test() {
        String username = "haha";
        String password = "1234";
        String email = "haha@nate.com";

        //userRepository.save(username, password, email);
    }


    @Test
    public void findById_test() {
        //given

        //when
        User user = userRepository.findById();
        //eye

        System.out.println(user.getUsername());


    }
}
