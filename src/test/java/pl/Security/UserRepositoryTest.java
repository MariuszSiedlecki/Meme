package pl.Security;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.controller.security.UserDto;
import pl.controller.security.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init(){
        System.out.println("We start testing ");
    }

    @Test
    public void CRUDOperationsUser(){
        UserDto userDto = new UserDto();
        userDto.setEmail("test@test.pl");
        userDto.setFirstName("Mariusz");
        userDto.setLastName("Siedlecki");
        userRepository.save(userDto);
        assertThat(userRepository.findById(userDto.getId())).isNotNull();

        String newLastName = "Populus";
        userDto.setLastName(newLastName);
        userRepository.save(userDto);
        assertThat(userRepository.findById(userDto.getId()).get().getLastName()).isEqualTo(newLastName);

        userRepository.delete(userDto);
        assertThat(userRepository.findById(userDto.getId())).isEmpty();
    }

    @After
    public void done() {
        System.out.println("We end testing UserRepository. ");
    }
}
