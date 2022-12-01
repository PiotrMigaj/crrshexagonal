package pl.migibud.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reservationName;
    private String conferenceRoomId;
}
