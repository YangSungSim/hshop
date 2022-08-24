package com.example.hshop.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Builder
@Getter
public class PlaceUpdateRequestDto {

        @Schema(description = "장소 상태")
        private Integer state;

        @Schema(description = "장소 이름")
        private String name;

        @Schema(description = "주소")
        private String address;

        @Schema(description = "여는 시간")
        private Time openAt;

        @Schema(description = "닫는 시간")
        private Time closeAt;

        @Schema(description = "예약 가능여부")
        private Boolean reserve;

        @Schema(description = "최대 예약 인원")
        private Integer reserveMax;
}
