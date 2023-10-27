//package com.example.ais_ecc.configurations;
//
//
//import com.example.ais_ecc.JwtTokenFilter;
//import com.example.ais_ecc.repositories.UserRepository;
//import com.example.ais_ecc.service.DbInit;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletResponse;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//    prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
//)
//public class SecurityJWTConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DbInit dbInit;
//    @Autowired private JwtTokenFilter jwtTokenFilter;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        dbInit.Init();
//
//        http.csrf().disable();
////        http.authorizeRequests().anyRequest().permitAll();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()
//                .antMatchers("/auth/login").permitAll()
//                .antMatchers("/mvc/**").permitAll()
//                .antMatchers("/api/**").permitAll()
//                .anyRequest().authenticated();
//
//        http.exceptionHandling()
//                .authenticationEntryPoint(
//                        (request, response, ex) -> {
//                            response.sendError(
//                                    HttpServletResponse.SC_UNAUTHORIZED,
//                                    ex.getMessage()
//                            );
//                        }
//                );
//
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(
//                username -> userRepo.findByEmail(username)
//                        .orElseThrow(
//                                () -> new UsernameNotFoundException("User " + username + " not found.")));
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//}
