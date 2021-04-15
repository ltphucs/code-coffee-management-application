package com.nobita.demo.service.impl;

import com.nobita.demo.model.Account;
import com.nobita.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account accountOptional =  accountService.findByUsername(s);
        System.out.println(accountOptional.toString());
        if(accountOptional == null){
            throw new UsernameNotFoundException("account not found");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        String authorization = String.valueOf(accountOptional.getAuthorization());
        authorities.add(new SimpleGrantedAuthority(authorization));
        return new org.springframework.security.core.userdetails.User(accountOptional.getUsername(), accountOptional.getPassword(), authorities);
    }
}
