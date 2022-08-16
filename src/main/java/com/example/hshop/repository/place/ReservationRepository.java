package com.example.hshop.repository.place;


import com.example.hshop.domain.place.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByIdAndStateIsLessThan(Long id, int limit);

    List<Reservation> findAllByStateIsLessThan(int limit, Pageable pageable);

    List<Reservation> findAllByStateIsLessThanAndAccountId(int limit, Long id, Pageable pageable);

    @Query("select r.place.id from Reservation r where r.state < 100 and r.account.id = ?1")
    List<Long> findPlaceIdByAccountId(Long id);

    List<Reservation> findAllByStateIsLessThanAndPlaceNameLike(int limit, String name, Pageable pageable);

    List<Reservation> findAllByStateIsLessThanAndAccountIdAndPlaceNameLike(int limit, Long id, String name, Pageable pageable);

}
