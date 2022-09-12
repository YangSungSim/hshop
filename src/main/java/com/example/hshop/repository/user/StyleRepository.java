package com.example.hshop.repository.user;
;
import com.example.hshop.domain.place.Place;
import com.example.hshop.domain.user.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StyleRepository  extends JpaRepository<Style, Long> {

    Optional<Style> findById(Long id);
}
