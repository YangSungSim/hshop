package com.example.hshop.domain.place;

import com.example.hshop.dto.place.PlaceUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date editedAt;

    @Column(length = 3, columnDefinition = "INT DEFAULT 0")
    private int state;

    @Column(length = 50, nullable = false)
    private String name;

    private String address;

    private Time openAt;

    private Time closeAt;

    private boolean reserve;

    private int reserveMax;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable
    private List<Reservation> reservation;

    public void update(PlaceUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getState()).ifPresent(e -> this.state = e);
        Optional.ofNullable(requestDto.getName()).ifPresent(e -> this.name = e);
        Optional.ofNullable(requestDto.getAddress()).ifPresent(e -> this.address = e);
        Optional.ofNullable(requestDto.getOpenAt()).ifPresent(e -> this.openAt = e);
        Optional.ofNullable(requestDto.getCloseAt()).ifPresent(e -> this.closeAt = e);
        Optional.ofNullable(requestDto.getReserve()).ifPresent(e -> this.reserve = e);
        Optional.ofNullable(requestDto.getReserveMax()).ifPresent(e -> this.reserveMax = e);
    }

    public void setState(Integer id) {
        this.state = id;
    }
}
