package com.example.hshop.dto.user;

import com.example.hshop.domain.user.Account;
import com.example.hshop.domain.user.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SignupForm {

    // "계정의 권한 종류(0:일반, 1:어드민)"
    @Nullable
    private Integer kind;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @Nullable
    private String phone;

    // make account with auth
    public Account toEntity(Set<Authority> authorities) {
        return Account.builder()
                .kind(kind)
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .authorities(authorities)
                .build();
    }
}
