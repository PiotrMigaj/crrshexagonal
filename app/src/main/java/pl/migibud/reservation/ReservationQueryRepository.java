package pl.migibud.reservation;

import pl.migibud.reservation.dto.ReservationView;

import java.util.List;

interface ReservationQueryRepository {
    List<ReservationView> findAllBy();
    List<ReservationView> findAllByConferenceRoomEntity_Id(String id);
    List<ReservationView> findAllById(String id);
}
