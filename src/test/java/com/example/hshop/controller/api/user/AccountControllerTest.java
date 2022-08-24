package com.example.hshop.controller.api.user;

import com.example.hshop.decorator.AccountDecorator;
import com.example.hshop.domain.user.Account;
import com.example.hshop.dto.user.AccountUpdateRequestDto;
import com.example.hshop.dto.user.LoginForm;
import com.example.hshop.dto.user.SignupForm;
import com.example.hshop.repository.user.AccountRepository;
import com.example.hshop.service.user.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AccountControllerTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    @Autowired
    AccountDecorator accountDecorator;

    @BeforeEach
    @Sql({"classpath:data.sql"})
    public void setUpBeforeClass() throws Exception {
        System.out.println("Test Data Create And Insert!");
    }

    @DisplayName("[API][login] 로그인")
    @Test
    void login() throws Exception {
        LoginForm login = new LoginForm();
        login.setName("홍길동");
        login.setPassword("1234");

        Account account = accountService.login(login);
        assertThat(account.getName()).isEqualTo("홍길동");

    }

    @Test
    void save() {
        //given
        SignupForm account1 = new SignupForm();
        account1.setKind(0);
        account1.setEmail("enduser@gmail.com");
        account1.setPassword("1234");
        account1.setName("enduser");
        account1.setPhone("01012345678");

        //when
        accountDecorator.save(account1);

        //then
        Optional<Account> result = accountService.findById(4L);
        assertThat(result.get().getName()).isEqualTo("enduser");
    }

    @Test
    @DisplayName("유저 정보 조회 성공 테스트")
    void findAll() throws Exception {
        //given
        SignupForm account1 = new SignupForm();
        account1.setKind(0);
        account1.setEmail("enduser@gmail.com");
        account1.setPassword("1234");
        account1.setName("enduser");
        account1.setPhone("01012345678");

        SignupForm account2 = new SignupForm();
        account2.setKind(0);
        account2.setEmail("abcd@gmail.com");
        account2.setPassword("1234");
        account2.setName("abcd");
        account2.setPhone("01012345678");

        //when
        accountDecorator.save(account1);
        accountDecorator.save(account2);

        //then
        List<Account> result = accountService.findAll();
        for (Account account : result) {
            System.out.println(account.getEmail());
        }
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    void findById() {
        //given
        SignupForm account1 = new SignupForm();
        account1.setKind(0);
        account1.setEmail("enduser@gmail.com");
        account1.setPassword("1234");
        account1.setName("enduser");
        account1.setPhone("01012345678");

        //when
        accountDecorator.save(account1);

        //then
        Optional<Account> result = accountService.findById(4L);
        assertThat(result.get().getName()).isEqualTo("enduser");
    }


    @Test
    @DisplayName("유저 정보 업데이트 테스트")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void update() {
        //given
        Optional<Account> target = accountService.findById(1L);
        AccountUpdateRequestDto requestDto = AccountUpdateRequestDto
                .builder()
                .kind(1)
                .name(target.get().getName())
                .password(target.get().getPassword())
                .email(target.get().getEmail())
                .phone(target.get().getPhone())
                .state(target.get().getState())
                .build();
        //when
        //try catch를 넣어야 부모 transactional 에서 데이터 입력을 마치고
        // transactoinal 에서 데이터 저장 작업을 시작할 수 있다.
        try {
            accountService.update(requestDto);
        } catch (IllegalArgumentException e) {
            System.out.println(">>> child service 예외 발생");
        }


        //then
        Optional<Account> result = accountService.findById(1L);
        assertThat(result.get().getKind()).isEqualTo(0);
    }
}