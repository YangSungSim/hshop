package com.example.hshop.controller.api.place;

import com.example.hshop.dto.ResultDto;
import com.example.hshop.dto.place.PlaceSaveRequestDto;
import com.example.hshop.dto.place.PlaceUpdateRequestDto;
import com.example.hshop.service.place.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.hshop.dto.ResultDto.makeResult;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/places")
public class PlaceApiController {

    private final PlaceService placeService;

    /**
     * to-do: 추가 삭제 수정 조회
     */

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("{id}")
    @Parameter(name = "id", required = true, example = "0")
    public ResponseEntity<ResultDto> findById(@PathVariable Long id) {
        return makeResult(HttpStatus.OK, placeService.findById(id));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("list/{sort}/{page}/{name}")
    public ResponseEntity<ResultDto> findAll(@PathVariable int sort, @PathVariable int page, @PathVariable String name) {
        return makeResult(HttpStatus.OK, placeService.findAll(sort, page, name));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<ResultDto> save(@RequestBody PlaceSaveRequestDto requestDto) {
        return makeResult(HttpStatus.OK, placeService.save(requestDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<ResultDto> update(@PathVariable Long id, @RequestBody PlaceUpdateRequestDto requestDto) {
        return makeResult(HttpStatus.OK, placeService.update(id, requestDto));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("{id}")
    @Operation(summary = "예약삭제")
    public ResponseEntity<ResultDto> deleteById(@PathVariable Long id) {
        return placeService.deleteById(id);
    }

}
