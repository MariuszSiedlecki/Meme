package pl.controller.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Getter
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private UserDto currentUser;
    private String emailCurrentUser;

    @Transactional
    public void registerUser(UserDto userDto) throws Exception {
        if (userEmailExists(userDto.getEmail())) {
            throw new Exception("user exists " + userDto.getEmail());
        }
        userDto.setRoles(Collections.singleton("USER"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userDto);
    }

    public boolean userEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userEmailExists(email)) {
            emailCurrentUser = email;
            return userRepository.findByEmail(email);
        } else {
            throw new UsernameNotFoundException("user not exists " + email);
        }
    }

    public UserDto getCurrentUser() {
        return emailCurrentUser != null && !emailCurrentUser.isEmpty() ? userRepository.findByEmail(emailCurrentUser) : null;
    }

    public void addOrDeleteMemeToFavoriteList(int meme_id) {
        if (getCurrentUser() != null) {
            getCurrentUser().addOrDeleteMemeToFavoriteList(meme_id);
            userRepository.save(getCurrentUser());
        }
    }

    public void deleteUser(UserDto userDto) {
        userRepository.delete(userDto);
    }

    //=========================
    public void deleteUser(int userId) {
        userRepository.delete(userRepository.findById(userId).get());
    }

    public void deleteUser(String email) {
        userRepository.delete(userRepository.findByEmail(email));
    }
}
