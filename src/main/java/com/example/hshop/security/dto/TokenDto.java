package com.example.hshop.security.dto;

import com.example.hshop.dto.user.AccountResponseDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"token","accountResponseDto"})
public class TokenDto {

    private String token;

    private AccountResponseDto accountResponseDto;
}
