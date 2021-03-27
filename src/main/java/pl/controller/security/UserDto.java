package pl.controller.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.model.Meme;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class UserDto implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String password;

    @NonNull
    @Email
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String>roles;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer>favoriteMemeIdList = new HashSet<>();

    @OneToMany(mappedBy = "userDtoOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Meme>memeList = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(roles);
    }
    public List<GrantedAuthority> getGrantedAuthorities(Set<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role :roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    public void addOrDeleteMemeToFavoriteList( int meme_id){
        if(this.favoriteMemeIdList.contains(meme_id)){
            this.favoriteMemeIdList.remove(meme_id);
        }else {
            this.favoriteMemeIdList.add(meme_id);
        }
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
