package com.example.hshop.dto.user;

import com.example.hshop.domain.user.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Getter
public class AccountResponseDto {

    private final Long id;    // 계정의 id

    // 계정의 권한 종류(0:일반, 1:어드민)
    private final int kind;

    // 계정의 상태
    private final int state;

    private final String email;   // 계정의 이메일이자 아이디

    private final String name;

    private final String phone;

    public AccountResponseDto(Account entity) {
        this.id = entity.getId();
        this.kind = entity.getKind();
        this.state = entity.getState();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.phone = entity.getPhone();
    }
}
