package com.example.hshop.dto.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"userName","password"})
public class LoginForm {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private String password;
}
