package com.example.hshop.controller.api.user;

import com.example.hshop.dto.user.LoginForm;
import com.example.hshop.dto.ResultDto;
import com.example.hshop.service.user.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.hshop.dto.ResultDto.makeResult;

@RequiredArgsConstructor
@RestController
@Tag(name = "계정 - jwt 관련 api")
@Slf4j
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/api/v1/authenticates")
    public ResponseEntity<ResultDto> authorize(@Valid @RequestBody LoginForm requestDto) {
        return makeResult(HttpStatus.OK, authService.authorize(requestDto));  //사용안함
    }

}
