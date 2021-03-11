package pl.Security;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.controller.security.UserDto;
import pl.controller.security.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserDto userDto;

    @Before
    public void init(){
        System.out.println("Start testing UserService: ");
    }

    @Test
    public void CRUDOperationUser() throws Exception {
        userService.registerUser(userDto);
        Mockito.verify(userService).registerUser(userDto);

        String emailUser="User@gmail.com";
        Mockito.when(userService.loadUserByUsername(emailUser)).thenReturn(userDto);
        Assert.assertEquals(userService.loadUserByUsername(emailUser),userDto);
    }

    @Test
    public void deleteUser() throws Exception {
    userService.deleteUser(userDto);
    Mockito.verify(userService).deleteUser(userDto);
    }

    @After
    public void done(){
        System.out.println("End testing UserService. ");
    }
}
