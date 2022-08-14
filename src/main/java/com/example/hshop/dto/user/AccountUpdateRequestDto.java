package com.example.hshop.dto.user;

import com.example.hshop.domain.user.Account;
import com.example.hshop.domain.user.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Builder
public class AccountUpdateRequestDto {

    // "계정의 권한 종류(0:일반, 1:어드민)"
    @Nullable
    private Integer kind;

    //계정의 상태
    @Nullable
    private Integer state;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @Nullable
    private String phone;
}
