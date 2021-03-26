package pl.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/favorite").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/add").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/yourmeme").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/h2-console/**").permitAll().and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
    }

}
