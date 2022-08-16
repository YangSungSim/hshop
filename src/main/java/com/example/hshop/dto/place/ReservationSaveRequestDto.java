package com.example.hshop.dto.place;

import com.example.hshop.domain.place.Place;
import com.example.hshop.domain.place.Reservation;
import com.example.hshop.domain.user.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReservationSaveRequestDto {
    @Schema(description = "장소 id")
    private Long placeId;

    @Schema(description = "예약시간")
    private Date reserveAt;

    public Reservation toEntity(Place place, Account account) {
        return Reservation.builder()
                .place(place)
                .account(account)
                .reserveAt(reserveAt)
                .build();
    }
}

