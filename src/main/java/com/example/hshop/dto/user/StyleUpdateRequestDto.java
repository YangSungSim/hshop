package com.example.hshop.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StyleUpdateRequestDto {

    @Schema(description = "헤어길이")
    private int hairLen;

    @Schema(description = "마지막 시술일자")
    private int lastPc;

    @Schema(description = "헤어 기장")
    private int hairLine;

    @Schema(description = "헤어 숱")
    private int plenty;

    @Schema(description = "모발 굵기")
    private int texture;

    @Schema(description = "모발 상태")
    private int health;

    @Schema(description = "탈색 여부")
    private String color;

    //사진 id 만 저장
    @Schema(description = "사진 아이디")
    private Long photo;

    @Schema(description = "어깨 넓이")
    private int shoulder;

    @Schema(description = "목 길이")
    private int neck;

    @Schema(description = "의견")
    private String comment;
}
