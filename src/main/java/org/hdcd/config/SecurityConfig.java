package org.hdcd.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    protected void configure(HttpSecurity http) throws Exception{
        log.info("security config");

//        http.authorizeRequests()
//                        .antMatchers("/member/register")
//                                .permitAll();

        http.formLogin();

    }
   // AuthenticationManagerBuilder는 인증 설정을 만들기 위한 빌더 스타일 인터페이스를 사용하는 configure()에 인자로 사용된다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
    //PasswordEncoder가 하는 역할은 이름에서 알수있듯 비밀번호를 암호화하는 역할이다
    @Bean
     public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
     }
}
