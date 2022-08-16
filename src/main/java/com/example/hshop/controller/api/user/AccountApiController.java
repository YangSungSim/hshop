package com.example.hshop.controller.api.user;

import com.example.hshop.decorator.AccountDecorator;
import com.example.hshop.domain.user.Account;
import com.example.hshop.dto.user.AccountUpdateRequestDto;
import com.example.hshop.dto.user.LoginForm;
import com.example.hshop.dto.user.SignupForm;
import com.example.hshop.dto.ResultDto;
import com.example.hshop.dto.user.AccountResponseDto;
import com.example.hshop.security.JwtFilter;
import com.example.hshop.security.dto.TokenDto;
import com.example.hshop.service.user.AccountService;
import com.example.hshop.service.user.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static com.example.hshop.dto.ResultDto.makeResult;

@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@RestController
@Slf4j
public class AccountApiController {

    private final AccountService accountService;

    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    private final AccountDecorator accountDecorator;

    @PostMapping("/login")
    public ResponseEntity<ResultDto> login(
            @Valid LoginForm loginForm
            , BindingResult bindingResult) {

        Account account = accountService.login(loginForm);

        if (bindingResult.hasErrors()) {
            return makeResult(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());
        }

        if (account == null)
            return makeResult(HttpStatus.BAD_REQUEST, "없는 계정입니다.");

        if (passwordEncoder.matches(loginForm.getPassword(), account.getPassword())) {
            //토큰을 여기서 발급
            String token = authService.authorize(loginForm);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);
            System.out.println("토큰 발급 완료");
            return makeResult(HttpStatus.OK, new TokenDto(token, new AccountResponseDto(account)));
        }
        return makeResult(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
    }

    @PostMapping()
    @Operation(summary = "회원 가입")
    public ResponseEntity<ResultDto> save(
            @Valid SignupForm signupForm
            , BindingResult bindingResult
    ) {
        log.info("/api/v1/account post controller");
        if (bindingResult.hasErrors()) {
            return makeResult(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());
        }

        if (accountService.save(signupForm) != null) {
            return makeResult(HttpStatus.INTERNAL_SERVER_ERROR, "이미 가입되어 있는 유저입니다.");
        }

        Account account = accountDecorator.save(signupForm);
        return makeResult(HttpStatus.OK, new AccountResponseDto(account));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("list")
    @Operation(summary = "회원 자신의 정보 조회")
    public ResponseEntity<ResultDto> findAll() {
        log.info("/api/v1/accounts/list get controller");
        List<Account> account =accountService.findAll();
        List<AccountResponseDto> accountResponseDtos = new ArrayList<>();
        for (Account account1 : account) {
            accountResponseDtos.add(new AccountResponseDto(account1));
        }
        return makeResult(HttpStatus.OK, accountResponseDtos);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("{id}")
    @Operation(summary = "회원 정보 조회")
    @Parameter(name = "id", required = true, example = "0")
    public ResponseEntity<ResultDto> findById(@PathVariable Long id) {
        log.info("/api/v1/account/{id} get controller");
        return makeResult(HttpStatus.OK, accountService.findById(id));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping()
    @Operation(summary = "회원 자신의 정보 조회")
    public ResponseEntity<ResultDto> findMe() {
        log.info("/api/v1/account get controller");
        return makeResult(HttpStatus.OK, accountService.findMe());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping()
    @Operation(summary = "회원 정보 수정-자신")
    public ResponseEntity<ResultDto> update(
            @Valid AccountUpdateRequestDto requestDto,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return makeResult(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());
        }

        log.info("/api/v1/account put controller");
        return makeResult(HttpStatus.OK, accountService.update(requestDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("{id}")
    @Operation(summary = "회원 정보 수정-다른사람")
    public ResponseEntity<ResultDto> update(
            @PathVariable Long id,
            @Valid AccountUpdateRequestDto requestDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return makeResult(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());
        }

        log.info("/api/v1/account/id put controller");

        return makeResult(HttpStatus.OK, accountService.update(id, requestDto));
    }


}
