package com.example.hshop.repository.user;

import com.example.hshop.domain.user.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository  extends JpaRepository<Account, Long> {
    // @EntityGraph는 쿼리가 수행이 될때 Lazy 조회가 아니고 Eager 조회로 Authorities 정보를 같이 가져오게 됨
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByName(String name);

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesById(Long id);

}
