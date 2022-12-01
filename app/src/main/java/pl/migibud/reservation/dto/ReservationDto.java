package pl.migibud.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ReservationDto {
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reservationName;
    private String conferenceRoomId;
}


