package com.example.hshop.controller.api.user;

import com.example.hshop.domain.user.Account;
import com.example.hshop.domain.user.Style;
import com.example.hshop.dto.ResultDto;
import com.example.hshop.dto.place.PlaceSaveRequestDto;
import com.example.hshop.dto.place.PlaceUpdateRequestDto;
import com.example.hshop.dto.user.AccountResponseDto;
import com.example.hshop.dto.user.StyleResponseDto;
import com.example.hshop.dto.user.StyleSaveRequestDto;
import com.example.hshop.dto.user.StyleUpdateRequestDto;
import com.example.hshop.service.user.AccountService;
import com.example.hshop.service.user.StyleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.hshop.dto.ResultDto.makeResult;

@RequiredArgsConstructor
@RequestMapping("/api/v1/style")
@RestController
@Slf4j
public class StyleApiController {

    private final StyleService styleService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("list")
    @Operation(summary = "회원 모두의 헤어의 상태 정보 조회")
    public ResponseEntity<ResultDto> findAll() {
        log.info("/api/v1/style/list get controller");
        List<Style> style =styleService.findAll();
        List<StyleResponseDto> styleResponseDtos = new ArrayList<>();
        for (Style style1 : style) {
            styleResponseDtos.add(new StyleResponseDto(style1));
        }
        return makeResult(HttpStatus.OK, styleResponseDtos);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("{id}")
    @Operation(summary = "특정 회원 헤어 정보 조회")
    @Parameter(name = "id", required = true, example = "0")
    public ResponseEntity<ResultDto> findById(@PathVariable Long id) {
        log.info("/api/v1/style/{id} get controller");
        return makeResult(HttpStatus.OK, styleService.findById(id));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping()
    @Operation(summary = "회원 자신의 헤어 정보 조회")
    public ResponseEntity<ResultDto> findMe() {
        log.info("/api/v1/account get controller");
        return makeResult(HttpStatus.OK, styleService.findMe());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping()
    public ResponseEntity<ResultDto> save(@RequestBody StyleSaveRequestDto requestDto) {
        return makeResult(HttpStatus.OK, styleService.save(requestDto));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<ResultDto> update(@PathVariable Long id, @RequestBody StyleUpdateRequestDto requestDto) {
        return makeResult(HttpStatus.OK, styleService.update(id, requestDto));
    }
}
