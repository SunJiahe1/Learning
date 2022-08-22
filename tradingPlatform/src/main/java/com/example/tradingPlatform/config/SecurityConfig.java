package com.example.tradingPlatform.config;

import com.example.tradingPlatform.dao.TradingDataMapper;
import com.example.tradingPlatform.pojo.Administrator;
import com.example.tradingPlatform.pojo.User;
import com.example.tradingPlatform.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    TradingDataMapper tradingDataMapper;

    @Bean
    public UserDetailsService myUserService(){
        return new UserDetailService();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/").permitAll()
                .antMatchers("/administration/**").hasAnyRole("administration")
                .antMatchers("/user/**").hasAnyRole("user")
                .antMatchers("/market/**").hasAnyRole("user");

        http.formLogin();

        http.logout().logoutSuccessUrl("/");

        http.rememberMe().rememberMeParameter("remember");

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<User> userList = tradingDataMapper.userList();
        for (User user : userList) {
            auth.inMemoryAuthentication()
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .withUser(user.getUser_name()).password(new BCryptPasswordEncoder().encode(user.getPassword())).roles("user");
        }
        List<Administrator> administratorList = tradingDataMapper.getAdministratorsList();
        for (Administrator administrator : administratorList) {
            auth.inMemoryAuthentication()
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .withUser(administrator.getAdministrator_name()).password(new BCryptPasswordEncoder().encode(administrator.getPassword())).roles("administration");
        }

        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("111111")).roles("USER")
                .and()
                .withUser("administration").password(new BCryptPasswordEncoder().encode("222222")).roles("administration");

        auth.userDetailsService(myUserService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
