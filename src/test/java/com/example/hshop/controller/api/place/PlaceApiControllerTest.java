package com.example.hshop.controller.api.place;

import com.example.hshop.dto.place.PlaceResponseDto;
import com.example.hshop.service.place.PlaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(PlaceApiControllerTest.class)
class PlaceApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceService placeService;

    @DisplayName("[API][FindById] 장소 상세보기")
    @Test
    void findById() throws Exception {
        /*given(placeService.findById(1L))
                .willReturn(new ResponseEntity(HttpStatus.OK, new PlaceResponseDto(1, 100)));*/
    }


    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }
}