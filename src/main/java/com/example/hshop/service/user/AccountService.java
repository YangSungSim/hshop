package com.example.hshop.service.user;

import com.example.hshop.domain.user.Account;
import com.example.hshop.dto.user.AccountUpdateRequestDto;
import com.example.hshop.dto.user.LoginForm;
import com.example.hshop.dto.user.SignupForm;
import com.example.hshop.repository.user.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private static final String ACCOUNT_NULL = "해당하는 유저가 없습니다.";
    @Transactional(readOnly = true)
    public Account login(LoginForm loginForm) {
        return accountRepository.findOneWithAuthoritiesByName(loginForm.getName()).orElse(null);
    }

    @Transactional
    public Account save(
            @Valid SignupForm requestDto) {
        return accountRepository.findOneWithAuthoritiesByName(requestDto.getName()).orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Account findMe() {
        Account account = com.example.hshop.security.util.SecurityUtil.getCurrentName().flatMap(accountRepository::findOneWithAuthoritiesByName)
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));
        return account;
    }
    @Transactional(readOnly = true)
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public Long update(AccountUpdateRequestDto requestDto) {
        Account account = com.example.hshop.security.util.SecurityUtil.getCurrentName().flatMap(accountRepository::findOneWithAuthoritiesByName)
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));

        if (requestDto!=null) {
            if (requestDto.getPassword() != null)
                requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            account.update(requestDto);
        }

        return account.getId();
    }

    @Transactional
    public Long update(Long id, AccountUpdateRequestDto requestDto) {
        Account account = accountRepository.findOneWithAuthoritiesById(id)
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL + " id="+id));

        if (requestDto!=null) {
            if (requestDto.getPassword() != null)
                requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            account.update(requestDto);
        }

        return account.getId();
    }


}
