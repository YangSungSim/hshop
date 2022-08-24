package com.example.hshop.controller.api.place;

import com.example.hshop.domain.place.Reservation;
import com.example.hshop.dto.ResultDto;
import com.example.hshop.dto.place.PlaceResponseDto;
import com.example.hshop.dto.place.ReservationSaveRequestDto;
import com.example.hshop.dto.place.ReservationUpdateRequestDto;
import com.example.hshop.repository.place.PlaceRepository;
import com.example.hshop.repository.place.ReservationRepository;
import com.example.hshop.service.place.PlaceService;
import com.example.hshop.service.place.ReservationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationApiControllerTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @BeforeEach
    @Sql({"classpath:data.sql"})
    public void setUpBeforeClass() throws Exception {
        System.out.println("Test Data Create And Insert!");
    }

    @Test
    void save() {
        //given
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto.builder()
                .placeId(2L)
                .reserveAt(null)
                .build();

        //when
        try {
            reservationService.save(requestDto);
        } catch (IllegalArgumentException e) {
            System.out.println(">>> child service 예외 발생");
        }

        //List<Reservation> reservation2 = reservationRepository.findAll();
        //Assertions.assertThat(reservation2.size()).isEqualTo(3);
    }

    @Test
    void update() {
        //given
        Optional<Reservation> reserve1 = reservationRepository.findById(1L);

        //when
        ReservationUpdateRequestDto requestDto = ReservationUpdateRequestDto.builder()
                .state(50)
                .placeId(reserve1.get().getId())
                .reserveAt(reserve1.get().getReserveAt())
                .build();

        try {
            reservationService.update(1L, requestDto);
        } catch (IllegalArgumentException e) {
            System.out.println(">>> child service 예외 발생");
        }

        //then
        Optional<Reservation> reservation2 = reservationRepository.findById(1L);
        Assertions.assertThat(reservation2.get().getState()).isEqualTo(50);

    }

    @Test
    void findById() {
        //when
        Optional<Reservation> reserve1 = reservationRepository.findById(1L);

        //then
        assertThat(reserve1.get().getId()).isEqualTo(1);
    }

    @Test
    void findAll() {
        //when
        List<Reservation> allList = reservationRepository.findAll();

        //then
        assertThat(allList.size()).isEqualTo(2);
    }
}