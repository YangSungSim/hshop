package com.example.hshop.domain.user;

import com.example.hshop.dto.user.AccountUpdateRequestDto;
import com.example.hshop.dto.user.StyleUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int hairLen;

    @Column(nullable = false)
    private int lastPc;

    @Column(nullable = false)
    private int hairLine;

    @Column(nullable = false)
    private int plenty;

    @Column(nullable = false)
    private int texture;

    @Column(nullable = false)
    private int health;

    @Column(nullable = false)
    private String color;

    //사진 id 만 저장
    @Column(nullable = false)
    private Long photo;

    @Column(nullable = false)
    private int shoulder;

    @Column(nullable = false)
    private int neck;

    @Column(nullable = true)
    private String comment;

    //고객 정보 랑 1:1 단방향
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Account account;

    //생성일시
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    //수정일시
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date editedAt;

    public void update(StyleUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getHairLen()).ifPresent(e -> this.hairLen = e);
        Optional.ofNullable(requestDto.getLastPc()).ifPresent(e -> this.lastPc = e);
        Optional.ofNullable(requestDto.getHairLine()).ifPresent(e -> this.hairLine = e);
        Optional.ofNullable(requestDto.getPlenty()).ifPresent(e -> this.plenty = e);
        Optional.ofNullable(requestDto.getTexture()).ifPresent(e -> this.texture = e);
        Optional.ofNullable(requestDto.getHealth()).ifPresent(e -> this.health = e);
        Optional.ofNullable(requestDto.getColor()).ifPresent(e -> this.color = e);
        Optional.ofNullable(requestDto.getPhoto()).ifPresent(e -> this.photo = e);
        Optional.ofNullable(requestDto.getShoulder()).ifPresent(e -> this.shoulder = e);
        Optional.ofNullable(requestDto.getNeck()).ifPresent(e -> this.neck = e);
        Optional.ofNullable(requestDto.getComment()).ifPresent(e -> this.comment = e);
    }

}
