package org.hdcd.config;


import lombok.RequiredArgsConstructor;
import org.hdcd.config.security.CustomAccessDeniedHandler;
import org.hdcd.config.security.CustomLoginSuccessHandler;
import org.hdcd.config.security.CustomUserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .antMatchers("/").permitAll()
                        .antMatchers("/auth/login").permitAll()
                        .antMatchers("member/register","./member/registerSuccess").permitAll()
                        .antMatchers("/member/**").hasRole("ADMIN")
                        .antMatchers("/codegroup/**").hasRole("ADMIN")
                        .antMatchers("/codedetail/**").hasRole("ADMIN")
                //회원게시판 웹 경로 보안지정
                        .anyRequest().authenticated();





        http.formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler());

        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());

        http.rememberMe()
                .key("hdcd")
                .tokenRepository(createJDBCRepository())
                .tokenValiditySeconds(60*60*24);

        http.logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me","JSESSION_ID");
    }

    private PersistentTokenRepository createJDBCRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
                repo.setDataSource(dataSource);
        return repo;
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
        }
    @Bean
        public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
        }

        @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }



}
