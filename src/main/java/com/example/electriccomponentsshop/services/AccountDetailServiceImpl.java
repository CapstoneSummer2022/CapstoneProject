package com.example.electriccomponentsshop.services;


import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email not found"));
        return AccountDetailImpl.build(account);
    }


}
