package org.hdcd.config;

import lombok.extern.slf4j.Slf4j;
import org.hdcd.security.CustomAccessDeiedHandler;
import org.hdcd.security.CustomLoginSuccessHandler;
import org.hdcd.security.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void configure(HttpSecurity http) throws Exception{
        log.info("security config");

//        http.authorizeRequests()
//                        .antMatchers("/member/register")
//                                .permitAll();

        http.formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                //로그인 성공후 처리를 담당하는 처리자
                .successHandler(authenticationSucessHandler());

        http.logout()
                        .logoutUrl("/auth/logout")
                                .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true)
                                                .deleteCookies("remember-me","JSESSION_ID");

        http.exceptionHandling()
                        .accessDeniedHandler(accessDeniedHandler());


        //데이터 소스를 지정하고 테이블을이용해 기존 로그인 정보를 기록
        http.rememberMe()
                .key("hdcd")
                .tokenRepository(createJDBCRepository())
                .tokenValiditySeconds(60*60*24);

    }

    private AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeiedHandler();
    }

    @Bean
    public CustomerUserDetailsService userDetailService(){
        return new CustomerUserDetailsService();
    }

    private PersistentTokenRepository createJDBCRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);

        return repo;
    }

    //로그인 성공처리자로 지정
    //스프링빈으로정의
    @Bean
    public AuthenticationSuccessHandler authenticationSucessHandler() {
    return new CustomLoginSuccessHandler();
    }

    // AuthenticationManagerBuilder는 인증 설정을 만들기 위한 빌더 스타일 인터페이스를 사용하는 configure()에 인자로 사용된다.


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailService())
                //사용자가 정의한 비밀번호 암호화 처리기를 지정
                        .passwordEncoder(passwordEncoder());
        super.configure(auth);
    }


    //PasswordEncoder가 하는 역할은 이름에서 알수있듯 비밀번호를 암호화하는 역할이다
    @Bean
     public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
     }
}
