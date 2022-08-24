package com.example.hshop.service.place;


import com.example.hshop.domain.place.Place;
import com.example.hshop.domain.user.Account;
import com.example.hshop.dto.ResultDto;
import com.example.hshop.dto.place.PlaceResponseDto;
import com.example.hshop.dto.place.PlaceSaveRequestDto;
import com.example.hshop.dto.place.PlaceUpdateRequestDto;
import com.example.hshop.model.StateKind;
import com.example.hshop.repository.place.PlaceRepository;
import com.example.hshop.repository.place.ReservationRepository;
import com.example.hshop.repository.user.AccountRepository;
import com.example.hshop.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.hshop.dto.ResultDto.makeResult;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final AccountRepository accountRepository;

    private final ReservationRepository reservationRepository;

    private static final String ACCOUNT_NULL = "해당하는 유저가 없습니다.";
    @Transactional
    public PlaceResponseDto findById(Long id) {
         Place place = placeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다. id=" + id));
         return new PlaceResponseDto(place);
    }

    @Transactional
    public List<PlaceResponseDto> findAll(int sort, int page, String name) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<Place> place = new ArrayList<>();

        boolean undefined = name != null && !name.equals(" ") && !name.equals("undefined");

        if (sort == 0) {
            if (undefined) {  //placename is not null
                System.out.println("findAllByNameLike");
                place = placeRepository.findAllByNameLike('%' + name + '%', pageRequest);
            }
            else {//placename is null
                System.out.println("findAllByStateIsLessThan");
                place = placeRepository.findAllByStateIsLessThan(StateKind.DELETE.getId(), pageRequest);
            }
        } else if (sort == 1) {

            Account account = SecurityUtil.getCurrentName()
                    .flatMap(accountRepository::findOneWithAuthoritiesByName)
                    .orElseThrow(() -> new IllegalArgumentException(ACCOUNT_NULL));

            System.out.println("user 이름:  "+ account.getId());

            List<Long> placeIds = reservationRepository.findPlaceIdByAccountId(account.getId());

            if(undefined)
                place = placeRepository.findAllByIdInAndNameLike(placeIds,'%'+name+'%',  pageRequest);
            else
                place = placeRepository.findAllByIdIn(placeIds, pageRequest);
        }

        List<PlaceResponseDto> result = new ArrayList<>();

        for (Place p : place) {
            result.add(new PlaceResponseDto(p));
        }
        return result;
    }

    @Transactional
    public Long update(Long id, PlaceUpdateRequestDto updateDto) {
       Place place =  placeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다. id=" + id));
       place.update(updateDto);
       return place.getId();
    }

    @Transactional
    public Long save(PlaceSaveRequestDto placeSaveDto) {
        return placeRepository.save(placeSaveDto.toEntity()).getId();
    }

    @Transactional
    public ResponseEntity<ResultDto> deleteById(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 존재하지 않습니다. id=" + id));


        place.setState(StateKind.DELETE.getId());
        return makeResult(HttpStatus.OK, id);
    }


}
