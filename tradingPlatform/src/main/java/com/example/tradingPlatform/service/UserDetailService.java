package com.example.tradingPlatform.service;

import com.example.tradingPlatform.dao.TradingDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    TradingDataMapper tradingDataMapper;

    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_user"));
        String password = tradingDataMapper.getPassword(user_name);

//        return new org.springframework.security.core.userdetails.User(user_name,
//                new BCryptPasswordEncoder().encode(password), authorities);

        return new User(user_name,new BCryptPasswordEncoder().encode(password), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_user"));
    }
}
