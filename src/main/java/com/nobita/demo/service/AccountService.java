package com.nobita.demo.service;


import com.nobita.demo.model.Account;

public interface AccountService extends BaseService<Account>{

    public Account findByUsername(String username);

}
