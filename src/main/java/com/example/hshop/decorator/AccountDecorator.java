package com.example.hshop.decorator;


import com.example.hshop.domain.user.Account;
import com.example.hshop.domain.user.Authority;
import com.example.hshop.dto.user.SignupForm;
import com.example.hshop.model.AccountRole;
import com.example.hshop.repository.user.AccountRepository;
import com.example.hshop.repository.user.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class AccountDecorator {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account save(SignupForm requestDto) {
        Authority authority = Authority.builder()
                .authorityName(AccountRole.valueOf(requestDto.getKind()))
                .build();

        if (authorityRepository.findByAuthorityName(AccountRole.ROLE_USER.name()).isEmpty()) {
            authorityRepository.save(authority);
        }

        // pw encoding
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        decorating(requestDto);
        return accountRepository.save(requestDto.toEntity(Collections.singleton(authority)));
    }

    private void decorating(SignupForm requestDto) {
        if (requestDto.getEmail().length() > 255) {
            requestDto.setEmail(slice(requestDto.getEmail(), 255));
        }
        if (requestDto.getName() != null && requestDto.getName().length() > 50) {   // 이름 끊기
            requestDto.setName(slice(requestDto.getName(), 50));
        }
        if (requestDto.getPhone() != null && requestDto.getPhone().length() > 20) {   // 이름 끊기
            requestDto.setPhone(slice(requestDto.getPhone(), 20));
        }
    }

    private String slice(String string, int max) {
        return string.substring(0, Math.min(string.length(), max));
    }
}
