package com.example.hshop.service.user;

import com.example.hshop.domain.place.Place;
import com.example.hshop.domain.user.Account;
import com.example.hshop.domain.user.Style;
import com.example.hshop.dto.place.PlaceSaveRequestDto;
import com.example.hshop.dto.place.PlaceUpdateRequestDto;
import com.example.hshop.dto.user.StyleSaveRequestDto;
import com.example.hshop.dto.user.StyleUpdateRequestDto;
import com.example.hshop.repository.user.AccountRepository;
import com.example.hshop.repository.user.StyleRepository;
import com.example.hshop.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class StyleService {

    private final StyleRepository styleRepository;

    private final AccountRepository accountRepository;

    private final String STYLE_NULL = "해당하는 스타일이 없습니다.";

    private static final String ACCOUNT_NULL = "해당하는 유저가 없습니다.";

    @Transactional(readOnly = true)
    public List<Style> findAll() {
        return styleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Style> findById(Long id) {
        return styleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Style> findMe() {
        Account account = com.example.hshop.security.util.SecurityUtil.getCurrentName().flatMap(accountRepository::findOneWithAuthoritiesByName)
                .orElseThrow(() -> new IllegalArgumentException(STYLE_NULL));
        Long id = account.getId();
        Optional<Style> style1 = styleRepository.findById(id);
        return style1;
    }

    @Transactional
    public Long save(StyleSaveRequestDto styleSaveDto) {

        Account account1 = SecurityUtil.getCurrentName().flatMap(accountRepository::findOneWithAuthoritiesByName)
                .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));

        return styleRepository.save(styleSaveDto.toEntity(account1)).getId();
    }

    @Transactional
    public Long update(Long id, StyleUpdateRequestDto updateDto) {
        Style style1 =  styleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다. id=" + id));
        style1.update(updateDto);
        return style1.getId();
    }
}
