package com.example.ais_ecc.configurations;


import com.example.ais_ecc.entity.User;
import com.example.ais_ecc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
)
public class SecurityDefaultConfig extends WebSecurityConfigurerAdapter {


    public SecurityDefaultConfig(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/login") // Указать страницу входа
                    .loginProcessingUrl("/auth/login")
                    .permitAll()
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/auth/login"); // Разрешить доступ к auth/login всем пользователям
        //.antMatchers("/home").authenticated() // Все остальные запросы требуют аутентификации
//                .and()
//                .formLogin()
//                .loginPage("/auth/login") // Указать страницу входа
//                .permitAll() // Разрешить всем пользователем доступ к странице входа
//                .and()
//                .logout()
//                .permitAll(); // Разрешить всем пользователям выход из системы


    }


    private final UserRepository userRepo;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                username -> userRepo.findByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User " + username + " not found.")));
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
