package com.example.hshop.controller.api.place;

import com.example.hshop.domain.place.Place;
import com.example.hshop.dto.place.PlaceResponseDto;
import com.example.hshop.dto.place.PlaceSaveRequestDto;
import com.example.hshop.dto.place.PlaceUpdateRequestDto;
import com.example.hshop.repository.place.PlaceRepository;
import com.example.hshop.service.place.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.UnexpectedRollbackException;


import javax.transaction.Transactional;

import java.sql.Time;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlaceApiControllerTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceService placeService;


    @BeforeEach
    @Sql({"classpath:data.sql"})
    public void setUpBeforeClass() throws Exception {
        System.out.println("Test Data Create And Insert!");
    }

    @DisplayName("[API][FindById] 장소 상세보기")
    @Test
    void findById() throws Exception {
        //when
        Long id = 1L;

        //then
        PlaceResponseDto result = placeService.findById(1L);
        assertThat(result.getName()).isEqualTo("testPlace01");
    }


    @Test
    void findAll() {
        //then
        List<PlaceResponseDto> result =  placeService.findAll(0,0,"testPlace01");
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void save() {
        PlaceSaveRequestDto requestDto = PlaceSaveRequestDto.builder()
                .name("testName zzz")
                .address("testAddress Zzz")
                .openAt(null)
                .closeAt(null)
                .reserve(false)
                .reserveMax(100)
                .build();

        Long id = 1L;
        //try {  이거 말고 class 상단에 @Transactional을 지우면 통과
            id = placeService.save(requestDto);
        //} catch (NullPointerException e) {
        //    System.out.println(">>> child service 예외 발생");
        //}

        //then
        PlaceResponseDto place2 = null;
        try {
            place2 = placeService.findById(id);
        } catch (NullPointerException e) {
            System.out.println(">>> child service 예외 발생");
        }

        assertThat(place2.getName()).isEqualTo("testName zzz");

    }

    @Test
    void update() {
        //given
        PlaceResponseDto place1 = placeService.findById(1L);
        PlaceUpdateRequestDto requestDto = PlaceUpdateRequestDto.builder()
                .name(place1.getName())
                .address(place1.getAddress())
                .state(place1.getState())
                .openAt(place1.getOpenAt())
                .closeAt(place1.getCloseAt())
                .reserve(true)
                .reserveMax(place1.getReserveMax())
                .build();

        //when
        try {
            placeService.update(1L,requestDto);
        } catch (IllegalArgumentException e) {
            System.out.println(">>> child service 예외 발생");
        }

        //then
        PlaceResponseDto place2 = placeService.findById(1L);
        assertThat(place2.getName()).isEqualTo("testPlace01");
    }
}