package com.example.hshop.security.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() {

    }

    public static Optional<String> getCurrentName() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String name = null;
        if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            name = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String e) {
            name = e;
        }

        return Optional.ofNullable(name);
    }
}
