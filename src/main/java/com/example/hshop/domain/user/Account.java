package com.example.hshop.domain.user;

import com.example.hshop.dto.user.AccountUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class Account {

    //고객번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //고객이름
    @Column(nullable = false)
    private String name;

    //고객 이메일
    @Column(nullable = false, unique = true)
    private String email;

    //고객 비밀번호
    @Column(nullable = false)
    private String password;

    //핸드폰번호
    @Column(nullable = false)
    private String phone;

    //권한정보
    @Column(length = 3)
    private int kind;

    //상태정보
    @Column(length = 3, columnDefinition = "INT DEFAULT 0")
    private int state;

    //생성일시
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    //수정일시
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date editedAt;

    //권한
    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "AccountId", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "AuthorityName", referencedColumnName = "AuthorityName")}
    )
    private Set<Authority> authorities = new java.util.LinkedHashSet<>();

    public void update(AccountUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getKind()).ifPresent(e -> this.kind = e);
        Optional.ofNullable(requestDto.getState()).ifPresent(e -> this.state = e);
        Optional.ofNullable(requestDto.getEmail()).ifPresent(e -> this.email = e);
        Optional.ofNullable(requestDto.getPassword()).ifPresent(e -> this.password = e);
        Optional.ofNullable(requestDto.getName()).ifPresent(e -> this.name = e);
        Optional.ofNullable(requestDto.getPhone()).ifPresent(e -> this.phone = e);
    }
}
