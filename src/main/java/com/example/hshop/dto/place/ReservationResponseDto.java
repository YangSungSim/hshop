package com.example.hshop.dto.place;

import com.example.hshop.domain.place.Reservation;
import com.example.hshop.dto.user.AccountResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReservationResponseDto {
    @Schema(description = "예약의 id")
    private final Long id;

    @Schema(description = "예약 상태")
    private final int state;

    @Schema(description = "장소")
    private final PlaceResponseDto place;

    @Schema(description = "예약한사람")
    private final AccountResponseDto account;

    @Schema(description = "예약시간")
    private final Date reserveAt;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.state = reservation.getState();
        this.place = new PlaceResponseDto(reservation.getPlace());
        this.account = new AccountResponseDto(reservation.getAccount());
        this.reserveAt = reservation.getReserveAt();
    }
}
