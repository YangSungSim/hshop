package com.example.hshop.dto.user;

import com.example.hshop.domain.user.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class StyleResponseDto {

    @Schema(description = "헤어 id")
    private final Long id;

    @Schema(description = "헤어길이")
    private final int hairLen;

    @Schema(description = "마지막 시술일자")
    private final int lastPc;

    @Schema(description = "헤어 기장")
    private final int hairLine;

    @Schema(description = "헤어 숱")
    private final int plenty;

    @Schema(description = "모발 굵기")
    private final int texture;

    @Schema(description = "모발 상태")
    private final int health;

    @Schema(description = "탈색 여부")
    private final String color;

    //사진 id 만 저장
    @Schema(description = "사진 아이디")
    private final Long photo;

    @Schema(description = "어깨 넓이")
    private final int shoulder;

    @Schema(description = "목 길이")
    private final int neck;

    @Schema(description = "의견")
    private final String comment;

    public StyleResponseDto(Style style) {
        this.id = style.getId();
        this.hairLen = style.getHairLen();
        this.lastPc = style.getLastPc();
        this.hairLine = style.getHairLine();
        this.plenty = style.getPlenty();
        this.texture = style.getTexture();
        this.health = style.getHealth();
        this.color = style.getColor();
        this.photo = style.getPhoto();
        this.shoulder = style.getShoulder();
        this.neck = style.getNeck();
        this.comment = style.getComment();

    }
}
