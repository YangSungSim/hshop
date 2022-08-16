package com.example.hshop.repository.place;

import com.example.hshop.domain.place.Place;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findById(Long id);
    List<Place> findAllByStateIsLessThan(int limit, Pageable pageable);

    List<Place> findAllByNameLike(String s, Pageable pageable);

    List<Place> findAllByIdInAndNameLike(Collection<Long> id, String name, PageRequest pageRequest);

    List<Place> findAllByIdIn(List<Long> placeIds, Pageable pageable);
}
