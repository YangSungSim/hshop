package com.example.hshop.service.user;

import com.example.hshop.domain.user.Account;
import com.example.hshop.domain.user.Authority;
import com.example.hshop.repository.user.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findOneWithAuthoritiesByName(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Account account) {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        String roles = account.getAuthorities().stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.joining(","));

        for(String role : roles.split(",")) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new User(
                String.valueOf(account.getName()),
                account.getPassword(),
                authorities
        );
    }
}
