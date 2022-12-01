package pl.migibud.reservation.dto;

import java.time.LocalDateTime;

public interface ReservationView {
    String getId();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    String getReservationName();
    String getConferenceRoomEntityId();
}
