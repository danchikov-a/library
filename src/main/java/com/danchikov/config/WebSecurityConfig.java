package com.danchikov.config;

import com.danchikov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String REGISTRATION_VIEW = "/registration";
    private static final String LOGIN_VIEW = "/login";
    private static final String MAIN_PAGE_VIEW = "/mainPage";
    private static final String OWN_PAGE_VIEW = "/ownPage";
    private static final String NO_VIEW = "/";
    private static final String USER_ROLE = "USER";
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        String[] staticResources = {"/css/*","/images/*"};

        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(REGISTRATION_VIEW).not().fullyAuthenticated()
                    //.antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers(NO_VIEW, MAIN_PAGE_VIEW).permitAll()
                    .antMatchers(OWN_PAGE_VIEW).hasRole(USER_ROLE)
                    .antMatchers(staticResources).permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage(LOGIN_VIEW)
                    .defaultSuccessUrl(OWN_PAGE_VIEW, true)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl(NO_VIEW);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
