package com.nobita.demo.service.impl;

import com.nobita.demo.dao.AccountDAO;
import com.nobita.demo.model.Account;
import com.nobita.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account findByID(Long id) {
        return accountDAO.findByID(id);
    }

    @Override
    public boolean save(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public boolean update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public boolean delete(Long id) {
        return accountDAO.delete(id);
    }

    @Override
    public Account findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return new MyAccountDetails(s);
//    }
}
