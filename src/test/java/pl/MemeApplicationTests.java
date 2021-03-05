package pl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.controller.MemeController;
import pl.controller.security.UserController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;


@RunWith(SpringRunner.class)
@SpringBootTest
 public class MemeApplicationTests {

    @Autowired
    MemeController memeController;

    @Autowired
    UserController userController;


    @Test
     public void contextLoads() {
        assertThat(memeController).isNotNull();
        assertThat(userController).isNotNull();
        assertThatIOException();
    }

}
